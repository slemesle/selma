package fr.xebia.extras.it.mappers;

import fr.xebia.extras.beans.PersonIn;
import fr.xebia.extras.beans.PersonOut;
import fr.xebia.extras.mapper.Mapper;

/**
 *
 *
 */
@Mapper(withMapper = CustomStaticMapper.class, ignoreMissingProperties = true)
public interface CustomMapperSupport {

    PersonOut mapWithCustom(PersonIn in);

}
