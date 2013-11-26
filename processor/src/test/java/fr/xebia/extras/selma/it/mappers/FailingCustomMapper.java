package fr.xebia.extras.selma.it.mappers;

import fr.xebia.extras.selma.beans.AddressIn;
import fr.xebia.extras.selma.beans.AddressOut;
import fr.xebia.extras.selma.Mapper;

/**
 *
 */
@Mapper(withMapper = {BadCustomMapper.class, EmptyCustomMapper.class })
public interface FailingCustomMapper {

    AddressOut asAddressOut(AddressIn in);
}
