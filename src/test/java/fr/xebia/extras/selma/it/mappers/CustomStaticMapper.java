package fr.xebia.extras.selma.it.mappers;

import fr.xebia.extras.selma.beans.CityIn;
import fr.xebia.extras.selma.beans.CityOut;
import fr.xebia.extras.selma.Factory;

/**
 *
 *
 */
public class CustomStaticMapper {


    public static CityOut mapCity(CityIn cityIn, Factory factory){
        CityOut cityOut = factory.newInstance(CityOut.class);
        cityOut.setName(cityIn.getName() + " Mapped by CustomStaticMapper");
        cityOut.setCapital(cityIn.isCapital());
        cityOut.setPopulation(cityIn.getPopulation() + 10000);
        return cityOut;
    }

}
