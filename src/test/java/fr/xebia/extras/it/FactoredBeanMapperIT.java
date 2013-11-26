package fr.xebia.extras.it;

import fr.xebia.extras.beans.*;
import fr.xebia.extras.it.mappers.BeanMapper;
import fr.xebia.extras.it.mappers.CustomStaticMapper;
import fr.xebia.extras.it.mappers.CustomMapperSupport;
import fr.xebia.extras.it.utils.Compile;
import fr.xebia.extras.it.utils.IntegrationTestBase;
import fr.xebia.extras.mapper.DefaultFactory;
import fr.xebia.extras.mapper.XMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Test mapper uses the factory
 */
@Compile( withClasses = {BeanMapper.class, CustomMapperSupport.class, CustomStaticMapper.class})
public class FactoredBeanMapperIT extends IntegrationTestBase {

    @Test
    public void mapper_should_use_the_given_factory(){

        TestFactory factory = new TestFactory();

        BeanMapper mapper =  XMapper.mapper(BeanMapper.class).withFactory(factory).build();

        PersonIn in = new PersonIn();
        in.setAddressBis(new AddressIn());
        in.setAddress(new AddressIn());
        in.getAddress().setCity(new CityIn());
        in.getAddressBis().setCity(new CityIn());

        PersonOut out =  mapper.convertFrom(in);


        Assert.assertEquals(
                new HashSet<>(Arrays.asList(PersonOut.class.getSimpleName(),
                        AddressOut.class.getSimpleName(),
                        CityOut.class.getSimpleName()))
                ,factory.builtClasses);

    }

   @Test
    public void custom_mapper_should_use_the_given_factory(){

       TestFactory factory = new TestFactory();

       CustomMapperSupport mapper =  XMapper.mapper(CustomMapperSupport.class).withFactory(factory).build();

       PersonIn in = new PersonIn();
       in.setAddressBis(new AddressIn());
       in.setAddress(new AddressIn());
       in.getAddress().setCity(new CityIn());
       in.getAddressBis().setCity(new CityIn());

       PersonOut out =  mapper.mapWithCustom(in);


       Assert.assertEquals(
               new HashSet<>(Arrays.asList(PersonOut.class.getSimpleName(),
                       AddressOut.class.getSimpleName(),
                       CityOut.class.getSimpleName()))
               ,factory.builtClasses);

   }


    private class TestFactory extends DefaultFactory {

        Set<String> builtClasses = new HashSet<>();

        @Override
        public <T> T newInstance(Class<T> aClass) {
            T t= super.newInstance(aClass);

            builtClasses.add(aClass.getSimpleName());
            return t;
        }
    }
}
