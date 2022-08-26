package com.example.demo.service.impl;

import com.example.demo.constants.GlobalConstants;
import com.example.demo.model.dto.OrderSeedDTO;
import com.example.demo.model.entity.Orders;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CustomerService customerService;
    private final ValidationUtil validationUtil;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, Gson gson, CustomerService customerService, ValidationUtil validationUtil, ProductService productService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.customerService = customerService;
        this.validationUtil = validationUtil;
        this.productService = productService;
    }

    @Override
    public void seedOrderDate() throws IOException {
        if (orderRepository.count() > 0) {
            return;
        }

        String fileContent = Files.readString
                (Path.of(GlobalConstants.INPUT_FILES_PATH + GlobalConstants.ORDERS));

        OrderSeedDTO[] orderSeedDTOS = gson.fromJson
                (fileContent, OrderSeedDTO[].class);


        Arrays.stream(orderSeedDTOS)
                .filter(validationUtil::isValid)
                .map(orderSeedDTO -> {
                    Orders order = modelMapper.map(orderSeedDTO, Orders.class);
                    order.setCustomer(customerService.findRandomCustomer());
                    order.setProducts(productService.findRandomProducts());
                    return order;
                })
                .forEach(orderRepository::save);
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> getRandomOrders() {
        int randomOrdersCount = ThreadLocalRandom.current().nextInt(1, 4);
        Long randomId = ThreadLocalRandom.current().nextLong(1, orderRepository.count() + 1);
        List<Orders> orders = new ArrayList<>();
        for (int i = 0; i < randomOrdersCount; i++) {
            orders.add(orderRepository.findById(randomId).orElse(null));
        }
        return orders;
    }

    @Override
    public Orders createAndSaveOrder() throws IOException {
        String fileContent = Files.readString(Path.of(GlobalConstants.INPUT_FILES_PATH + GlobalConstants.SINGLE_ORDER));

        OrderSeedDTO orderSeedDTO = gson.fromJson(fileContent, OrderSeedDTO.class);
        Orders order;
        if (validationUtil.isValid(orderSeedDTO)) {
            order = modelMapper.map(orderSeedDTO, Orders.class);
            order.setCustomer(customerService.findRandomCustomer());
            order.setProducts(productService.findRandomProducts());
            orderRepository.save(order);
        } else {
            order = null;
        }
        return order;
    }

    @Override
    public Orders findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Orders> orders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders createOrderWithoutMandatoryFields() {
        Orders order = new Orders();
        order.setId(null);
        order.setOrderCompleted(null);
        order.setOrderPayed(null);
        order.setDateOfOrder(null);
        order.setDateOrderCompleted(null);
        return null;
    }

    @Override
    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }

}
