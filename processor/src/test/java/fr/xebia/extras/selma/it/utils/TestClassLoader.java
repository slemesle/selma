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
package fr.xebia.extras.selma.it.utils;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 27/11/2013
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class TestClassLoader extends ClassLoader {

    private final ClassLoader compiledClassesLoader;

    public TestClassLoader(ClassLoader parent, ClassLoader compiledClassesLoader) {
        super(parent);
        this.compiledClassesLoader = compiledClassesLoader;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> zeClass = null;

        try {
            zeClass = compiledClassesLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            zeClass = super.findClass(name);
        }

        return zeClass;
    }
}
