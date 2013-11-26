package fr.xebia.extras.selma.beans;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 21/11/2013
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class CityIn {

    private  String name;
    private  long population;
    private  boolean capital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }
}
