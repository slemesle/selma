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
package fr.xebia.extras.selma.it;

import fr.xebia.extras.selma.DefaultFactory;
import fr.xebia.extras.selma.Selma;
import fr.xebia.extras.selma.beans.*;
import fr.xebia.extras.selma.it.mappers.BeanMapper;
import fr.xebia.extras.selma.it.mappers.CustomMapperSupport;
import fr.xebia.extras.selma.it.mappers.CustomStaticMapper;
import fr.xebia.extras.selma.it.mappers.DataSourceBeanMapper;
import fr.xebia.extras.selma.it.mappers.DataSourceBeanMapper;
import fr.xebia.extras.selma.it.utils.Compile;
import fr.xebia.extras.selma.it.utils.IntegrationTestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Test selma uses the factory
 */
@Compile(withClasses = {BeanMapper.class, CustomMapperSupport.class, CustomStaticMapper.class})
public class FactoredBeanMapperIT extends IntegrationTestBase {

    @Test
    public void mapper_should_use_the_given_factory() {

        TestFactory factory = new TestFactory();

        BeanMapper mapper = Selma.mapper(BeanMapper.class).withFactory(factory).build();

        PersonIn in = new PersonIn();
        in.setAddressBis(new AddressIn());
        in.setAddress(new AddressIn());
        in.getAddress().setCity(new CityIn());
        in.getAddressBis().setCity(new CityIn());

        PersonOut out = mapper.convertFrom(in);


        Assert.assertEquals(
                new HashSet<String>(Arrays.asList(PersonOut.class.getSimpleName(),
                        AddressOut.class.getSimpleName(),
                        CityOut.class.getSimpleName()))
                , factory.builtClasses);

    }

    @Test
    public void custom_mapper_should_use_the_given_factory() {

        TestFactory factory = new TestFactory();

        CustomMapperSupport mapper = Selma.mapper(CustomMapperSupport.class).withFactory(factory).build();

        PersonIn in = new PersonIn();
        in.setAddressBis(new AddressIn());
        in.setAddress(new AddressIn());
        in.getAddress().setCity(new CityIn());
        in.getAddressBis().setCity(new CityIn());

        PersonOut out = mapper.mapWithCustom(in);


        Assert.assertEquals(
                new HashSet<String>(Arrays.asList(PersonOut.class.getSimpleName(),
                        AddressOut.class.getSimpleName(),
                        CityOut.class.getSimpleName()))
                , factory.builtClasses);

    }

    private class TestFactory extends DefaultFactory {

        Set<String> builtClasses = new HashSet<String>();

        @Override
        public <T> T newInstance(Class<T> aClass) {
            T t = super.newInstance(aClass);

            builtClasses.add(aClass.getSimpleName());
            return t;
        }
    }
}
