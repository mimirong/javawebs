package cn.com.hugedata.spark.admin.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {

    /**
     * 是否需要登录.
     * @return 是否需要登录
     */
    boolean required() default true;
}
