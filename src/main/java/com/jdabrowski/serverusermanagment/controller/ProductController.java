package com.jdabrowski.serverusermanagment.controller;

import com.jdabrowski.serverusermanagment.model.Product;
import com.jdabrowski.serverusermanagment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/user/home")
    public ResponseEntity<?> saveProduct(@RequestBody String productName) throws Exception {
        Product product = productService.getProductByName(productName);
        if(product != null){
            long amount = product.getAmount() + 1;
            product.setAmount(amount);
            productService.updateAndSaveProduct(product);
        } else {
            productService.createAndSaveProduct(productName);
        }
        return new ResponseEntity<>(productName, HttpStatus.CREATED);
    }

    @GetMapping("/api/user/products")
    public ResponseEntity<?> getAllProducts() throws Exception {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
