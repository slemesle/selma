package fr.xebia.extras.selma.it.mappers;

import fr.xebia.extras.selma.beans.PersonIn;
import fr.xebia.extras.selma.beans.PersonOut;
import fr.xebia.extras.selma.Mapper;

/**
 *
 *
 */
@Mapper(withMapper = CustomStaticMapper.class, ignoreMissingProperties = true)
public interface CustomMapperSupport {

    PersonOut mapWithCustom(PersonIn in);

}
