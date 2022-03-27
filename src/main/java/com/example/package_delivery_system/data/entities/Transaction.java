package com.example.package_delivery_system.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private Customer payerId;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "amount")
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "package_id")
    private Package packageId;


    public Customer getPayerId() {
        return payerId;
    }

    public void setPayerId(Customer payerId) {
        this.payerId = payerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Package getPackageId() {
        return packageId;
    }

    public void setPackageId(Package packageId) {
        this.packageId = packageId;
    }
}
