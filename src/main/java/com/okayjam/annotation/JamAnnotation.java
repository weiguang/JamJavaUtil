package com.okayjam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/09/06 11:24
 **/
//@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JamAnnotation {
    String value() default "Jam";
}
