package fr.xebia.extras.selma.beans;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 26/11/2013
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class Customer {

    private String name;
    private Address address;
    private String phoneNumber;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
