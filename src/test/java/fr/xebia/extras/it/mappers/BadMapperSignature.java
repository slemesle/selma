package fr.xebia.extras.it.mappers;

import fr.xebia.extras.mapper.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 22/11/2013
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
@Mapper
public interface BadMapperSignature {

    String mapTwoParameters (String in, String inBis);


    String mapDifferentTypes (boolean in);

}
