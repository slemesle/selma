package fr.xebia.extras.selma.beans;

import fr.xebia.extras.selma.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 27/11/2013
 * Time: 00:10
 * To change this template use File | Settings | File Templates.
 */
@Mapper(ignoreMissingProperties = true)
public interface Order2OrderDto {

    OrderDto to(Order in);

}
