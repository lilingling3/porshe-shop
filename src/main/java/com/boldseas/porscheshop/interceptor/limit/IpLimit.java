package com.boldseas.porscheshop.interceptor.limit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * IP调用限制，对应某个接口或者方法名称
 *
 * @author fei.ye
 * @version 2018/5/9.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IpLimit {
    /**
     * 该接口或者方法可以被调用的次数
     *
     * @return 默认10次
     */
    int times() default 10;

    /**
     * 该限制持续多久
     *
     * @return 默认一分钟
     */
    int seconds() default 60;
}
