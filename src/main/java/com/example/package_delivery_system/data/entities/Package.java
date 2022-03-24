package com.example.package_delivery_system.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "packages")
public class Package extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "address_to", nullable = false)
    private Address addressTo;

    @ManyToOne
    @JoinColumn(name = "address_from", nullable = false)
    private Address addressFrom;

    @Column(name = "content")
    private String content;

    @Column(name = "is_delivered", columnDefinition = "tinyint(1) default 0")
    private boolean isDelivered;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;

    @OneToOne(mappedBy = "packageId")
    private Transaction transaction;

    public Address getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(Address addressTo) {
        this.addressTo = addressTo;
    }

    public Address getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(Address addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getDeliveryTax() {
        return deliveryTax;
    }

    public void setDeliveryTax(BigDecimal deliveryTax) {
        this.deliveryTax = deliveryTax;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
