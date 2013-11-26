package fr.xebia.extras.beans;

import java.util.Collection;
import java.util.Date;

/**
 *
 */
public class PersonOut {

    private String firstName;
    private String lastName;
    private Date birthDay;

    private int age;


    private Long[] indices;

    private Collection<String> tags;

    private EnumOut enumIn;
    private AddressOut address;
    private AddressOut addressBis;

    public EnumOut getEnumIn() {
        return enumIn;
    }

    public void setEnumIn(EnumOut enumIn) {
        this.enumIn = enumIn;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public Long[] getIndices() {
        return indices;
    }

    public void setIndices(Long[] indices) {
        this.indices = indices;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AddressOut getAddress() {
        return address;
    }

    public void setAddress(AddressOut address) {
        this.address = address;
    }

    public AddressOut getAddressBis() {
        return addressBis;
    }

    public void setAddressBis(AddressOut addressBis) {
        this.addressBis = addressBis;
    }
}
