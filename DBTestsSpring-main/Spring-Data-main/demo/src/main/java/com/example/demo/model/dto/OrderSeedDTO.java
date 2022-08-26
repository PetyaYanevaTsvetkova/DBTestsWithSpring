package com.example.demo.model.dto;

import com.google.gson.annotations.Expose;

public class OrderSeedDTO {
    @Expose
    private Boolean isOrderCompleted;
    @Expose
    private Boolean isOrderPayed;
    @Expose
    private String dateOfOrder;
    @Expose
    private String dateOrderCompleted;

    public OrderSeedDTO() {
    }

    public Boolean getOrderCompleted() {
        return isOrderCompleted;
    }

    public void setOrderCompleted(Boolean orderCompleted) {
        isOrderCompleted = orderCompleted;
    }

    public Boolean getOrderPayed() {
        return isOrderPayed;
    }

    public void setOrderPayed(Boolean orderPayed) {
        isOrderPayed = orderPayed;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getDateOrderCompleted() {
        return dateOrderCompleted;
    }

    public void setDateOrderCompleted(String dateOrderCompleted) {
        this.dateOrderCompleted = dateOrderCompleted;
    }
}
