package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassInfo {
    String mainAuthor();
    String className();
    String[] pillarsUsed();
    String[] solidUsed();
}