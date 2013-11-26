package fr.xebia.extras.selma.it.mappers;

import fr.xebia.extras.selma.beans.PersonOut;
import fr.xebia.extras.selma.beans.PersonIn;
import fr.xebia.extras.selma.Mapper;

/**
 *
 */
@Mapper(ignoreMissingProperties = true)
public interface BeanMapper {

    PersonOut convertFrom(PersonIn in);

}
