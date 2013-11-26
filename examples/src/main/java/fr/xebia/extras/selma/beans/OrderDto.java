package fr.xebia.extras.selma.beans;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class OrderDto {

    private CustomerDto customer;
    private List<ProductDto> products;
    private SalesChannelDto salesChannel;
    private long totalAmount;
    private Date creationDate;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public SalesChannelDto getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(SalesChannelDto salesChannel) {
        this.salesChannel = salesChannel;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
