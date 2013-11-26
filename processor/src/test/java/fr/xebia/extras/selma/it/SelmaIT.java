package fr.xebia.extras.selma.it;

import fr.xebia.extras.selma.it.mappers.BadMapperSignature;
import fr.xebia.extras.selma.it.mappers.SelmaTestMapper;
import fr.xebia.extras.selma.it.utils.Compile;
import fr.xebia.extras.selma.it.utils.IntegrationTestBase;
import fr.xebia.extras.selma.DefaultFactory;
import fr.xebia.extras.selma.Factory;
import fr.xebia.extras.selma.Selma;
import org.junit.Assert;
import org.junit.Test;

/**
 */
@Compile(withClasses = SelmaTestMapper.class)
public class SelmaIT extends IntegrationTestBase {

    @Test
    public void xmapper_builder_should_return_same_mapper_in_two_times_call(){

        SelmaTestMapper mapper = Selma.mapper(SelmaTestMapper.class).build();

        Assert.assertTrue(mapper == Selma.mapper(SelmaTestMapper.class).build());

    }

    @Test
    public void xmapper_static_should_return_same_mapper_in_two_times_call(){

        SelmaTestMapper mapper = Selma.getMapper(SelmaTestMapper.class);

        Assert.assertTrue(mapper == Selma.getMapper(SelmaTestMapper.class));

    }

    @Test
    public void xmapper_builder_with_factory_should_return_same_mapper_in_two_times_call(){

        Factory factory =  new DefaultFactory();
        SelmaTestMapper mapper = Selma.mapper(SelmaTestMapper.class).withFactory(factory).build();

        Assert.assertTrue(mapper == Selma.mapper(SelmaTestMapper.class).withFactory(factory).build());
    }

    @Test
    public void xmapper_static_with_factory_should_return_same_mapper_in_two_times_call(){

        Factory factory =  new DefaultFactory();
        SelmaTestMapper mapper = Selma.getMapper(SelmaTestMapper.class, factory);

        Assert.assertTrue(mapper == Selma.getMapper(SelmaTestMapper.class, factory));
    }


    @Test(expected = IllegalArgumentException.class)
    public void xmapper_should_raise_illegalArgException_on_not_existing_mapper(){

        BadMapperSignature mapper = Selma.getMapper(BadMapperSignature.class);

    }






}
