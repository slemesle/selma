package fr.xebia.extras.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

/**
 * Annotation de Mapper
 */
@Target({ElementType.TYPE})
@Inherited
public @interface Mapper {

    /**
     * Wether compilation should fail when one field from in bean is missing in out bean<br/>
     * By default, compilation will fail and report error. Setting this to true will allow XMapper to skip
     * the missing field NO MAPPING CODE WILL BE GENERATED FOR THE MISSING FIELD.
     */
    boolean ignoreMissingProperties() default false;

    /**
     * Wether compilation should fail when XMapper finds a situation where it can not generate mapping code.<br/>
     * Reason is not supported in base code for this Type to Type conversion.
     * By default compilation should fail at code generation time.
     * If you prefer to generate a method that raises UnsupportedException when trying to map the field set this to true.
     */
    boolean ignoreNotSupported() default false;


    /**
     * Add a list of custom mapper class
     * @return
     */
    Class<?>[] withMapper() default {};


}
