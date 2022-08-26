package com.example.demo.model.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    private String name;
    private Integer quantity;
    private String type;
    private BigDecimal price;
    private Boolean isProductInStock;
    private String warehouse;

    public Product() {
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "is_product_in_stock", nullable = false)
    public Boolean getProductInStock() {
        return isProductInStock;
    }

    public void setProductInStock(Boolean productInStock) {
        isProductInStock = productInStock;
    }

    @Column(name = "warehouse", nullable = false)
    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(quantity, product.quantity)
                && Objects.equals(type, product.type) && Objects.equals(price, product.price)
                && Objects.equals(isProductInStock, product.isProductInStock)
                && Objects.equals(warehouse, product.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, type, price, isProductInStock, warehouse);
    }
}
