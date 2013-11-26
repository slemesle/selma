package fr.xebia.extras.it.mappers;

import fr.xebia.extras.beans.AddressIn;
import fr.xebia.extras.beans.AddressOut;
import fr.xebia.extras.mapper.Mapper;

/**
 *
 */
@Mapper(withMapper = {BadCustomMapper.class, EmptyCustomMapper.class })
public interface FailingCustomMapper {

    AddressOut asAddressOut(AddressIn in);
}
