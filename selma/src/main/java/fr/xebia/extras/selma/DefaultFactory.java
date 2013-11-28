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

/**
 * This is the default factory that creates bean using the default void constructor.
 * Mappers retrieved in Selma without a provided Factory will use this class to instantiate beans.
 */
public class DefaultFactory implements Factory {

    @Override
     public <T> T newInstance(Class<T> aClass) {
        try {
            return aClass.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(String.format("Unable to instantiate class %s with default no args constructor (Constructor does not exist ADD DEFAULT CONSTRUCTOR !)", aClass.getName()), e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(String.format("Unable to instantiate class %s with default no args constructor (Constructor is not accessible FIX MODIFIERS !)", aClass.getName()), e);
        }
    }
}
