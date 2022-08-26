package com.example.demo.model.entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {
    private Boolean isOrderCompleted;
    private Boolean isOrderPayed;
    private LocalDate dateOfOrder;
    private LocalDate dateOrderCompleted;
    private Customer customer;
    private Set<Product> products;

    public Orders() {
    }

    @Column(name = "is_order_completed", nullable = false)
    public Boolean getOrderCompleted() {
        return isOrderCompleted;
    }

    public void setOrderCompleted(Boolean orderCompleted) {
        isOrderCompleted = orderCompleted;
    }

    @Column(name = "is_order_payed", nullable = false)
    public Boolean getOrderPayed() {
        return isOrderPayed;
    }

    public void setOrderPayed(Boolean orderPayed) {
        isOrderPayed = orderPayed;
    }

    @Column(name = "date_of_order", nullable = false)
    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    @Column(name = "date_order_completed")
    public LocalDate getDateOrderCompleted() {
        return dateOrderCompleted;
    }

    public void setDateOrderCompleted(LocalDate dateOrderCompleted) {
        this.dateOrderCompleted = dateOrderCompleted;
    }

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
