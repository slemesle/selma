/*
 * Copyright 2013  Séven Le Mesle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package fr.xebia.extras.selma;

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
     * By default, compilation will fail and report error. Setting this to true will allow Selma to skip
     * the missing field NO MAPPING CODE WILL BE GENERATED FOR THE MISSING FIELD.
     */
    boolean ignoreMissingProperties() default false;

    /**
     * Wether compilation should fail when Selma finds a situation where it can not generate mapping code.<br/>
     * Reason is not supported in base code for this Type to Type conversion.
     * By default compilation should fail at code generation time.
     * If you prefer to generate a method that raises UnsupportedException when trying to map the field set this to true.
     */
    boolean ignoreNotSupported() default false;


    /**
     * Add a list of custom mapper class.
     * A custom mapper is a class that gives one or more method :
     *
     * public static OutType methodName(InType in, Factory factory)
     *
     * These methods will be called to handle custom mapping of in bean to the OutType
     */
    Class<?>[] withMapper() default {};


}
