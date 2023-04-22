package com.example.javastudy.safe;


import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Prevent {


    // 限制的时间值
    String value() default "60";

    // 提示
    String message() default "操作过于频繁，请稍后再试";

    // 策略
    PreventStrategy strategy() default PreventStrategy.DEFAULT;
}
