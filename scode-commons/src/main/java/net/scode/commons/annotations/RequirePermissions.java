package net.scode.commons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要权限才能访问的注解
 *
 * @author tanghuang 2020年02月27日
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermissions {

    String[] value();

    Logical logical() default Logical.AND;

}
