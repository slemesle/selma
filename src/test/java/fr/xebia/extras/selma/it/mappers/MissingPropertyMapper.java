package fr.xebia.extras.selma.it.mappers;

import fr.xebia.extras.selma.beans.PersonIn;
import fr.xebia.extras.selma.beans.PersonOut;
import fr.xebia.extras.selma.Mapper;

/**
 *
 */
@Mapper
public interface MissingPropertyMapper {

    PersonOut map(PersonIn in);

}
