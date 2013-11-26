package fr.xebia.extras.selma.beans;

import java.util.Collection;
import java.util.Date;

/**
 *
 */
public class PersonIn {


    private String firstName;
    private String lastName;
    private Date birthDay;

    private int age;


    private boolean male;

    private Long[] indices;

    private Collection<String> tags;

    private EnumIn enumIn;
    private AddressIn address;
    private AddressIn addressBis;

    public EnumIn getEnumIn() {
        return enumIn;
    }

    public void setEnumIn(EnumIn enumIn) {
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


    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public void setAddress(AddressIn address) {
        this.address = address;
    }

    public AddressIn getAddress() {
        return address;
    }

    public AddressIn getAddressBis() {
        return addressBis;
    }

    public void setAddressBis(AddressIn addressBis) {
        this.addressBis = addressBis;
    }
}
