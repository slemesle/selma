package fr.xebia.extras.selma.it.utils;


import java.lang.annotation.*;

/**
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface Compile {

    /**
     * Add all classes inside the package to compilation
     * @return
     */
    String [] withPackage() default {};

    /**
     * Add listed classes to compilation
     * @return
     */
    Class<?> [] withClasses() default {};

    boolean shouldFail() default  false;
}
