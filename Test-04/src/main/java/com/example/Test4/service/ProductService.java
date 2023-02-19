package com.example.Test4.service;

import com.example.Test4.model.Product;
import com.example.Test4.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepository ProductRepository;

    public void addProduct(Product product) {
        ProductRepository.save(product);
    }

    public Product getProductById(Integer productId) {
        return ProductRepository.findById(productId).get();
    }

    public List<Product> getAllProducts() {
        return ProductRepository.findAll();
    }

    public void updateProductDetails(Product product) {
        Product productToBeUpdated =  ProductRepository.findById(product.getProduct_id()).get();
        productToBeUpdated.setName(product.getName());
        productToBeUpdated.setCategory(product.getCategory());
        productToBeUpdated.setPrice(product.getPrice());
        productToBeUpdated.setBrand(product.getBrand());
        productToBeUpdated.setDescription(product.getDescription());
        ProductRepository.save(productToBeUpdated);
    }

    public void deleteProductWithProduct_id(Integer id) {
        ProductRepository.deleteById(id);
    }

    public List<Product> getProductsOfSuchCategory(String category) {
        List<Product> productListOfDesiredCategory = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        productList = ProductRepository.findAll();
        for(Product p:productList){
            if(p.getCategory().equals(category)){
                productListOfDesiredCategory.add(p);
            }
        }
        return productListOfDesiredCategory;
    }
}
