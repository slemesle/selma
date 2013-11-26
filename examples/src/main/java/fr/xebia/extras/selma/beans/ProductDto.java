package fr.xebia.extras.selma.beans;

import java.util.Map;
import java.util.Set;

/**
 *
 */
public class ProductDto {

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

}
