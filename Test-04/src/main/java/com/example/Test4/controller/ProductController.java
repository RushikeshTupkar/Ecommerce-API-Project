package com.example.Test4.controller;

import com.example.Test4.model.Product;
import com.example.Test4.service.ProductService;
import com.example.Test4.util.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductValidation productValidation;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {

        boolean doesCategoryMatches = productValidation.checkProductCategory(product.getCategory());
        if(!doesCategoryMatches){
            return new ResponseEntity<>("Product category does not match" +
                    " pleas find categories of products here - 1:XUV , 2:SEDAN, 3:HATCHBACK , 4:4*4 , 5:TRUCK ", HttpStatus.OK);
        }

        if (productValidation.checkProductIsPresentOrNot(product.getProduct_id())) {
            return new ResponseEntity<>("Product already exists with product_id - " + product.getProduct_id(), HttpStatus.BAD_REQUEST);
        } else {
            productService.addProduct(product);
            return new ResponseEntity<>("Product saved having product_id - " + product.getProduct_id(), HttpStatus.OK);
        }
    }

    @GetMapping("getProduct_By_product_id/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer product_id) {
        if (productValidation.checkProductIsPresentOrNot(product_id)) {
            return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAll_products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/update_product_details")
        public ResponseEntity<String> updateUser(@RequestBody Product product){
        if (!productValidation.checkProductIsPresentOrNot(product.getProduct_id())) {
            return new ResponseEntity<>("No product fond with product_id - "+product.getProduct_id(),HttpStatus.BAD_REQUEST);
        }else{
            productService.updateProductDetails(product);
            return new ResponseEntity<>("Product updated having product_id - "+product.getProduct_id(),HttpStatus.OK);
        }
        }

        @DeleteMapping("/deleteProduct_with_product_id/{id}")
        public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
            if (!productValidation.checkProductIsPresentOrNot(id)) {
                return new ResponseEntity<>("No product fond with product_id - "+id,HttpStatus.BAD_REQUEST);
            }else{
                productService.deleteProductWithProduct_id(id);
                return new ResponseEntity<>("Product deleted having product_id - "+id,HttpStatus.OK);
            }
        }

        @GetMapping("/getProduct_based_on_category")
    public Map<String,List<Product>> getProducts(@RequestParam("category") String category){
            boolean doesCategoryMatches = productValidation.checkProductCategory(category);
            Map<String,List<Product>>a = new HashMap<>();
            if(doesCategoryMatches){
                List<Product>productListOfDesiredCategory =productService.getProductsOfSuchCategory(category);
                a.put("List of products of category - "+ category, productListOfDesiredCategory);
                return a;
            }
            else{
                a.put("Product category does not match" +
                        " pleas find categories of products here - " +
                        "1:XUV , 2:SEDAN, 3:HATCHBACK , 4:4*4 , 5:TRUCK ",null);
                return a;
            }

        }
}
