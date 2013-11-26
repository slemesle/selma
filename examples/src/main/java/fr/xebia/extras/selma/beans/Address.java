package fr.xebia.extras.selma.beans;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 26/11/2013
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class Address {

    private String streetLine1;
    private String streetLine2;
    private String cityLine;

    public String getStreetLine2() {
        return streetLine2;
    }

    public void setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    public String getCityLine() {
        return cityLine;
    }

    public void setCityLine(String cityLine) {
        this.cityLine = cityLine;
    }

    public String getStreetLine1() {
        return streetLine1;
    }

    public void setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
    }
}
