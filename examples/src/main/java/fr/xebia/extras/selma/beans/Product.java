package fr.xebia.extras.selma.beans;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 26/11/2013
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */
public class Product {

    private Map<String, String> logisticMap;
    private String code;
    private String label;
    private double price;
    private Set<String> tags;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Map<String, String> getLogisticMap() {
        return logisticMap;
    }

    public void setLogisticMap(Map<String, String> logisticMap) {
        this.logisticMap = logisticMap;
    }
}
