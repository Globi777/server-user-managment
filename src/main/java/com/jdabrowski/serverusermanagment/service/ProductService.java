package com.jdabrowski.serverusermanagment.service;

import com.jdabrowski.serverusermanagment.model.Product;

import java.util.List;

public interface ProductService {

    void updateAndSaveProduct(Product product);

    void createAndSaveProduct(String productName);

    Product getProductById (String id) throws Exception;

    Product getProductByName (String name);

    List<Product> getAllProducts ();


}
