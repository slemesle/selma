package fr.xebia.extras.it;

import fr.xebia.extras.it.mappers.BadMapperSignature;
import fr.xebia.extras.it.mappers.SimpleMapper;
import fr.xebia.extras.it.mappers.XMapperTestMapper;
import fr.xebia.extras.it.utils.Compile;
import fr.xebia.extras.it.utils.IntegrationTestBase;
import fr.xebia.extras.mapper.DefaultFactory;
import fr.xebia.extras.mapper.Factory;
import fr.xebia.extras.mapper.XMapper;
import org.junit.Assert;
import org.junit.Test;

/**
 */
@Compile(withClasses = XMapperTestMapper.class)
public class XMapperIT extends IntegrationTestBase {

    @Test
    public void xmapper_builder_should_return_same_mapper_in_two_times_call(){

        XMapperTestMapper mapper = XMapper.mapper(XMapperTestMapper.class).build();

        Assert.assertTrue(mapper == XMapper.mapper(XMapperTestMapper.class).build());

    }

    @Test
    public void xmapper_static_should_return_same_mapper_in_two_times_call(){

        XMapperTestMapper mapper = XMapper.getMapper(XMapperTestMapper.class);

        Assert.assertTrue(mapper == XMapper.getMapper(XMapperTestMapper.class));

    }

    @Test
    public void xmapper_builder_with_factory_should_return_same_mapper_in_two_times_call(){

        Factory factory =  new DefaultFactory();
        XMapperTestMapper mapper = XMapper.mapper(XMapperTestMapper.class).withFactory(factory).build();

        Assert.assertTrue(mapper == XMapper.mapper(XMapperTestMapper.class).withFactory(factory).build());
    }

    @Test
    public void xmapper_static_with_factory_should_return_same_mapper_in_two_times_call(){

        Factory factory =  new DefaultFactory();
        XMapperTestMapper mapper = XMapper.getMapper(XMapperTestMapper.class, factory);

        Assert.assertTrue(mapper == XMapper.getMapper(XMapperTestMapper.class, factory));
    }


    @Test(expected = IllegalArgumentException.class)
    public void xmapper_should_raise_illegalArgException_on_not_existing_mapper(){

        BadMapperSignature mapper = XMapper.getMapper(BadMapperSignature.class);

    }






}
