package com.example.javastudy.skill.checker.handler;


import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;
import com.example.javastudy.skill.checker.CheckerException;
import com.example.javastudy.skill.checker.Condition;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("all")
public class ConditionHandler {


    public static boolean handleCustom(Object obj, Condition condition) {
        if (condition.getResultPredicate().apply(obj)) {
            throw new CheckerException(condition.getDesc());
        }
        return true;
    }

    public static boolean handleNe(Object obj, Condition condition) {
        try {
            Class<?> targetClass = obj.getClass();
            Field field = targetClass.getDeclaredField(condition.getField());
            field.setAccessible(true);
            Object result = field.get(obj);
            if (Objects.equals(result, condition.getVal())) {
                throwBizError(condition);
            }
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throwFieldError(condition);
        }
        return true;
    }

    private static void throwFieldError(Condition condition) {
        throwMsg(String.format("字段(%s)不存在", condition.getField()));
    }

    private static void throwBizError(Condition condition) {
        String msg = StringUtils.isEmpty(condition.getDesc()) ? String.format("字段(%s)值错误", condition.getField()) : condition.getDesc();
        throwMsg(msg);
    }

    public static boolean handleNotNull(Object obj, Condition condition) {
        try {
            Class<?> targetClass = obj.getClass();
            Field field = targetClass.getDeclaredField(condition.getField());
            field.setAccessible(true);
            Object result = field.get(obj);
            if (Objects.isNull(result)) {
                throwBizError(condition);
            }
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throwFieldError(condition);
        }
        return true;
    }

    public static boolean handleNotNullNe(Object obj, Condition condition) {
        try {
            Class<?> targetClass = obj.getClass();
            Field field = targetClass.getDeclaredField(condition.getField());
            field.setAccessible(true);
            Object result = field.get(obj);
            if (Objects.isNull(result) || result.equals(condition.getVal())) {
                throwBizError(condition);
            }
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throwFieldError(condition);
        }
        return true;
    }

    public static boolean handleIn(Object obj, Condition condition) {

        try {
            Class<?> targetClass = obj.getClass();
            Field field = targetClass.getDeclaredField(condition.getField());
            field.setAccessible(true);
            Object result = field.get(obj);
            Object val = condition.getVal();
            if (val instanceof Collection) {
                Collection<?> temp = (Collection<?>) val;
                if (Objects.isNull(result) || !temp.contains(result)) {
                    throwBizError(condition);
                }
            }
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throwFieldError(condition);
        }
        return true;

    }

    public static void throwMsg(String msg) {
        throw new CheckerException(msg);
    }

}
