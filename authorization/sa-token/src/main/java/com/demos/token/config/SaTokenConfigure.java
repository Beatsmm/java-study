package com.demos.token.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    // 注册Sa-Token拦截器，打开注解式鉴权功能

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

    /**
     * 全局过滤器
     * 优点：
     * 1、相比于拦截器，过滤器更加底层，执行时机更靠前，有利于防渗透扫描
     * 2、过滤器可以拦截静态资源，方便做一些权限控制
     * 3、部分web框架根本就没有提供拦截器功能，但是几乎所有的web框架都有过滤器功能
     * 缺点：
     * 1、由于太过底层，导致无法率先拿到HandlerMethod对象,无法根据此添加一些额外功能
     * 2、由于拦截的太全面了,导致我们需要很多特殊路由(如/favicon.ico)做一些额外处理
     * 3、在Spring中,过滤器抛出的异常无法进入全局@ExceptionHandler我们必须额外编写代码进行异常处理
     */

    @Bean
    public SaServletFilter getSaServletFilter(){
        return new SaServletFilter()
                // 指定拦截路由与放行路由
                .addInclude("/**") // .addExclude("/favicon.ico")
                // 认证函数：每次请求执行
                .setAuth(obj -> {
                    // System.out.println("---------- sa全局认证 " + SaHolder.getRequest().getRequestPath());
                    // SaManager.getLog().debug("----- 请求path={}  提交token={}", SaHolder.getRequest().getRequestPath(), StpUtil.getTokenValue());

                    // 权限校验 -- 不同模块认证不同权限
                    //		这里你可以写和拦截器鉴权同样的代码，不同点在于：
                    // 		校验失败后不会进入全局异常组件，而是进入下面的 .setError 函数
                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
                    SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
                    SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
                    SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
                    SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));
                })
                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> {
                    System.out.println("---------- sa全局异常 ");
                    return SaResult.error(e.getMessage());
                })
                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer("sa-server")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                    ;
                })
                ;

    }

    /**
     * 重写 Sa-Token 框架内部算法策略
     */
    @Autowired
    public void rewriteSaStrategy() {
        // 重写Sa-Token的注解处理器，增加注解合并功能
        SaStrategy.me.getAnnotation = (element, annotationClass) -> {
            return AnnotatedElementUtils.getMergedAnnotation(element, annotationClass);
        };
    }

}
