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

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Selma is the only class used to gain access to the Mappers implementations generated at compile time.
 *
 * It offers a Builder API to retrieve the Mapper you want :
 * <code>
 *      // Without factory
 *     Selma.mapper(MapperInterface.class).build();
 *
 *     // With factory
 *     Selma.mapper(MapperInterface.class).withFactory(factoryInstance).build();
 * </code>
 *
 * It also offers two simple static methods getMapper(MapperInterface.class) offering the same service level.
 *
 * Please notice that Selma holds a cache of the instantiated Mappers, and will maintain them as Singleton.
 *
 */
public class Selma {

    private static final Map<String, Object> mappers = new ConcurrentHashMap<String, Object>();

    /**
     * Retrieve the generated Mapper for the corresponding interface in the classpath and instantiate it with default factory.
     *
     * @param mapperClass   The Mapper interface class
     * @param <T>           The Mapper interface itself
     * @return              A new Mapper instance or previously instantiated selma
     *
     * @throws IllegalArgumentException If for some reason the Mapper class can not be loaded or instantiated
     */
    public static <T> T getMapper(Class<T> mapperClass) throws IllegalArgumentException {
         return getMapper(mapperClass, null);
    }

    /**
     * Mapper Builder DSL for those who like it like that.
     * @param mapperClass   The Mapper interface class
     * @param <T>           The Mapper interface itself
     * @return              Builder for Mapper
     */
    public static <T> XMapperFactoryBuilder<T> mapper(Class<T> mapperClass) {

        return new XMapperFactoryBuilder<T>(mapperClass);
    }

    /**
     * Retrieve the generated Mapper for the corresponding interface in the classpath.
     *
     * @param mapperClass   The Mapper interface class
     * @param factory       The factory to be used for bean instantiation or null for default
     * @param <T>           The Mapper interface itself
     * @return              A new Mapper instance or previously instantiated selma
     *
     * @throws IllegalArgumentException If for some reason the Mapper class can not be loaded or instantiated
     */
    public synchronized static <T> T getMapper(Class<T> mapperClass, Factory factory) throws IllegalArgumentException {

        final String mapperKey = String.format("%s-%s", mapperClass.getCanonicalName(), factory);
        if (!mappers.containsKey(mapperKey)) {



            // First look for the context class loader
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            if ( classLoader == null ) {
                classLoader = Selma.class.getClassLoader();
            }

            @SuppressWarnings("unchecked")
            T mapperInstance = null;
            try {

                Class<T> mapperImpl  = (Class<T>) classLoader.loadClass(mapperClass.getCanonicalName() + SelmaConstants.MAPPER_CLASS_SUFFIX);
                if(factory != null){
                     mapperInstance =  mapperImpl.getDeclaredConstructor(Factory.class).newInstance(factory);
                } else {
                    mapperInstance = (T) mapperImpl.newInstance();
                }
            } catch (InstantiationException e) {
                throw new IllegalArgumentException(String.format("Instantiation of Mapper class %s failed : %s", mapperClass.getName(), e.getMessage()), e);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(String.format("Instantiation of Mapper class %s failed : %s", mapperClass.getName(), e.getMessage()), e);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(String.format("Instantiation of Mapper class %s failed : %s", mapperClass.getName(), e.toString()), e);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(String.format("Instantiation of Mapper class %s failed (No constructor with Factory parameter !) : %s", mapperClass.getName(), e.getMessage()), e);
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException(String.format("Instantiation of Mapper class %s failed (No constructor with Factory parameter !) : %s", mapperClass.getName(), e.getMessage()), e);
            }

            mappers.put(mapperKey, mapperInstance);
            return mapperInstance;
        }

        return (T) mappers.get(mapperKey);
    }

    /**
     * Builder style API to get Mapper for holding the class parameter
     * @param <T>
     */
    public static final class XMapperFactoryBuilder<T> {
        private final Class<T> mapperClass;

        public XMapperFactoryBuilder(Class<T> mapperClass) {
            this.mapperClass = mapperClass;
        }

        public T build(){
            return Selma.getMapper(mapperClass);
        }

        public XMapperFactoredBuilder<T> withFactory(Factory factory){
            return new XMapperFactoredBuilder<T>(mapperClass, factory);
        }
    }

    /**
     * Builder style API to get Mapper with a provided Factory
     * @param <T>
     */
    public static class XMapperFactoredBuilder<T> {
        private final Class<T> mapperClass;
        private final Factory factory;

        public XMapperFactoredBuilder(Class<T> mapperClass, Factory factory) {
            this.mapperClass = mapperClass;
            this.factory = factory;
        }

        public T build(){
            return Selma.getMapper(mapperClass, factory);
        }
    }

}
