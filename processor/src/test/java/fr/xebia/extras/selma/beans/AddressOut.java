package fr.xebia.extras.selma.beans;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 21/11/2013
 * Time: 09:31
 * To change this template use File | Settings | File Templates.
 */
public class AddressOut {

    CityOut city;
    String street;
    int number;
    List<String> extras;
    boolean principal;

    public CityOut getCity() {
        return city;
    }

    public void setCity(CityOut city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
