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
package fr.xebia.extras.selma.it.mappers;

import fr.xebia.extras.selma.beans.CityIn;
import fr.xebia.extras.selma.beans.CityOut;
import fr.xebia.extras.selma.Factory;

/**
 *
 */
public class BadCustomMapper {


    CityOut notPublicNotStaticMethod(CityIn in, Factory factory){
        return null;
    }

    public static void voidMethod(CityIn in, Factory factory){
        return ;
    }

    public static CityOut noParameterMethod(){
        return null;
    }

    public static CityOut oneParameterMethod( CityIn in){
        return null;
    }

    public static CityOut threeParameterMethod( CityIn in, CityIn in2, CityIn in3){
        return null;
    }

    public static CityOut withoutFactoryMethod( CityIn in, CityIn in2){
        return null;
    }


}
