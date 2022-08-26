package com.example.demo.service;

import com.example.demo.model.entity.Orders;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    void seedOrderDate() throws IOException;

    List<Orders> getAllOrders();

    List<Orders> getRandomOrders();

    Orders createAndSaveOrder() throws IOException;

    Orders findById(Long id);

    List<Orders> orders();

    Orders createOrderWithoutMandatoryFields();

    void saveOrder(Orders order);
}
