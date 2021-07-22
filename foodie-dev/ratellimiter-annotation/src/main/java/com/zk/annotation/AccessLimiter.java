package com.zk.annotation;

import java.lang.annotation.*;

/**
 * @author CoderZk
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimiter {

    String limit();

    String methodKey() default "";
}
