package com.example.javastudy.skill.sign;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 在AOP中如果获取了request的body内容,那么在控制层,在使用@RequestBody注解的话就会获取不到body的内容了,
 * 因为request的inputStream只能被读取一次,解决这个问题一个简单方式就是使用request的包装对象ContentCachingRequestWrapper
 */
@Component
public class RequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestWrapper = request;
        if (isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
            requestWrapper = new ContentCachingRequestWrapper(request);
        }
        try {
            filterChain.doFilter(requestWrapper, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
