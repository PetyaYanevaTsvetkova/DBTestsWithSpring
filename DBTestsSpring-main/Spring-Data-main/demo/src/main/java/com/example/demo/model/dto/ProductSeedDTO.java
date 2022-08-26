package com.example.demo.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProductSeedDTO {
    @Expose
    private String name;
    @Expose
    private Integer quantity;
    @Expose
    private String type;
    @Expose
    private BigDecimal price;
    @Expose
    private Boolean isProductInStock;
    @Expose
    private String warehouse;

    public ProductSeedDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getProductInStock() {
        return isProductInStock;
    }

    public void setProductInStock(Boolean productInStock) {
        isProductInStock = productInStock;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
}
