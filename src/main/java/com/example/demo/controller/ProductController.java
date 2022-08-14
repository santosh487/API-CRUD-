package com.example.demo.controller;


import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService ;

    //create
    @PostMapping
    public ResponseEntity<Product>  createProduct(@RequestBody Product product) {
        Product product1 = this.productService.create(product);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int productId) {
        Product product1 = this.productService.update(product,productId);
        return new ResponseEntity<>(product1,HttpStatus.OK);
    }
    //delete
     @DeleteMapping("/{productId}")
    public ResponseEntity<Map<String,String>> deleteProduct(@PathVariable int productId) {
        this.productService.delete(productId);
        Map<String,String> message = Map.of("message","product deleted successfully");
        return new ResponseEntity<>(message,HttpStatus.OK);
     }
    //get single product
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId) {
        Product product1 = this.productService.getById(productId);
        return new ResponseEntity<>(product1,HttpStatus.OK);
    }

    //get all product
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> all = this.productService.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}
