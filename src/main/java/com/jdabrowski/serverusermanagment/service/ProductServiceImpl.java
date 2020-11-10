package com.jdabrowski.serverusermanagment.service;

import com.jdabrowski.serverusermanagment.model.Product;
import com.jdabrowski.serverusermanagment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateAndSaveProduct(Product product) {
        repository.save(product);
    }

    public void createAndSaveProduct(String productName){
        String id = UUID.randomUUID().toString();
        Product newProduct = new Product(id, productName);
        repository.save(newProduct);
    }

    @Override
    public Product getProductById(String id){
        Product product = repository.findById(Long.valueOf(id)).orElse(null);
        return product;
    }

    @Override
    public Product getProductByName(String name){
        Product product = repository.findByName(name).orElse(null);
        return product;
    }

    @Override
    public List<Product> getAllProducts(){
        return repository.findAll();
    }


}
