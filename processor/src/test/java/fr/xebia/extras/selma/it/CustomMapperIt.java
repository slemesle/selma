package fr.xebia.extras.selma.it;

import fr.xebia.extras.selma.beans.AddressIn;
import fr.xebia.extras.selma.beans.CityIn;
import fr.xebia.extras.selma.beans.PersonIn;
import fr.xebia.extras.selma.beans.PersonOut;
import fr.xebia.extras.selma.it.mappers.CustomStaticMapper;
import fr.xebia.extras.selma.it.mappers.CustomMapperSupport;
import fr.xebia.extras.selma.it.utils.Compile;
import fr.xebia.extras.selma.it.utils.IntegrationTestBase;
import fr.xebia.extras.selma.Selma;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 */
@Compile(withClasses = {CustomStaticMapper.class, CustomMapperSupport.class})
public class CustomMapperIt extends IntegrationTestBase {


    @Test
    public void should_map_bean_with_custom_mapper() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        CustomMapperSupport mapper = Selma.getMapper(CustomMapperSupport.class);

        PersonIn personIn = new PersonIn();
        personIn.setAddress(new AddressIn());
        personIn.getAddress().setCity(new CityIn());
        personIn.getAddress().getCity().setCapital(true);
        personIn.getAddress().getCity().setName("Paris");
        personIn.getAddress().getCity().setPopulation(3 * 1000 * 1000);

        personIn.getAddress().setPrincipal(true);
        personIn.getAddress().setNumber(55);
        personIn.getAddress().setStreet("rue de la truanderie");

        PersonOut res = mapper.mapWithCustom(personIn);

        Assert.assertNotNull(res);

        Assert.assertEquals(personIn.getAddress().getStreet(), res.getAddress().getStreet());
        Assert.assertEquals(personIn.getAddress().getNumber(), res.getAddress().getNumber());
        Assert.assertEquals(personIn.getAddress().getExtras(), res.getAddress().getExtras());

        Assert.assertEquals(personIn.getAddress().getCity().getName() + " Mapped by CustomStaticMapper", res.getAddress().getCity().getName());
        Assert.assertEquals(personIn.getAddress().getCity().getPopulation() + 10000, res.getAddress().getCity().getPopulation());
        Assert.assertEquals(personIn.getAddress().getCity().isCapital(), res.getAddress().getCity().isCapital());
    }
}
