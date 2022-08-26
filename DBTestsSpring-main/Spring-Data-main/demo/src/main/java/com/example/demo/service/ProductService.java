package com.example.demo.service;

import com.example.demo.model.entity.Orders;
import com.example.demo.model.entity.Product;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {
    void seedProductData() throws IOException;

    Set<Product> findRandomProducts();

    List<Product> findAllProducts();

    Product createAndSaveProduct() throws IOException;

    Product createProductWithoutMandatoryFields();

    void saveProduct(Product product);

    Product findById(Long idSavedProduct);
}
