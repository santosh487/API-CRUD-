package com.example.demo.services.impl;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Product product, int productId) {
        Product  product1 = this.productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product not found!!"));

        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setAbout(product.getAbout());

        Product save = this.productRepository.save(product1);
        return save;
    }

    @Override
    public void delete(int productId) {
        Product product = this.productRepository.findById(productId).
                orElseThrow(()->new RuntimeException("Product not found"));
        this.productRepository.delete(product);


    }

    @Override
    public Product getById(int productId) {
        Product product = this.productRepository.findById(productId).
                orElseThrow(()->new RuntimeException("Product not found"));
        return product;
    }

    @Override
    public List<Product> getAll() {

        List<Product> all = this.productRepository.findAll();
        return all;
    }
}
