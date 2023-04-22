package com.example.javastudy.safe;


import com.example.javastudy.skill.json.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Base64;

@Aspect
@Component
@Slf4j
public class PreventAop {

    @Autowired
    private RedisUtil redisUtil;


    // 切入点
    @Pointcut("@annotation(com.example.javastudy.safe.Prevent)")
    public void pointcut() {
    }


    // 处理前
    @Before("pointcut()")
    public void joinPoint(JoinPoint joinPoint) throws Exception {
        String req = JacksonUtil.toString(joinPoint.getArgs()[0]);
        if (StringUtils.isEmpty(req)) {
            log.info("req is null");
            return;
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getClass().
                getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        Prevent preventAnnotation = method.getAnnotation(Prevent.class);
        String methodFullName = method.getDeclaringClass().getName() + method.getName();

        entrance(preventAnnotation, req,methodFullName);
    }

    private void entrance(Prevent prevent, String requestStr,String methodFullName) throws Exception{
        PreventStrategy strategy = prevent.strategy();
        switch (strategy) {
            case DEFAULT:
                defaultHandle(requestStr, prevent,methodFullName);
                break;
            default:
                throw new BusinessException("无效的策略");
        }
    }

    // 默认处理方式
    private void defaultHandle(String requestStr, Prevent prevent,String methodFullName) throws Exception {
        String base64Str = toBase64String(requestStr);
        long expire = Long.parseLong(prevent.value());
        String resp = redisUtil.get(methodFullName+base64Str);
        if (StringUtils.isEmpty(resp)) {
            redisUtil.set(methodFullName+base64Str, requestStr, expire);
        } else {
            String message = !StringUtils.isEmpty(prevent.message()) ? prevent.message() :
                    expire + "秒内不允许重复请求";
            throw new BusinessException(message);
        }
    }


    // 对象转换为base64字符串
    private String toBase64String(String obj) throws Exception {
        if (StringUtils.isEmpty(obj)) {
            return null;
        }
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = obj.getBytes("UTF-8");
        return encoder.encodeToString(bytes);
    }


}
