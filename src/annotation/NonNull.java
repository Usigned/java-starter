package annotation;

import java.lang.annotation.Repeatable;

@Repeatable(Schedules.class)
public @interface NonNull {
    String name() default "LZQ";
}
