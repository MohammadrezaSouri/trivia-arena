package annotation;

import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface Information {
    String information() default "";
    String difficulty() default "";
}
