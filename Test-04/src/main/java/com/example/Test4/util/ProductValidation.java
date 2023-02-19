package com.example.Test4.util;

import com.example.Test4.model.Product;
import com.example.Test4.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidation {

    @Autowired
    IProductRepository ProductRepository;
    public  boolean checkProductIsPresentOrNot(Integer id){
        List<Integer> list = new ArrayList<>();
        list.add(id);
        List<Product>userList = new ArrayList<>();
        userList = (ProductRepository.findAllById(list));
        if(!userList.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkProductCategory(String category) {
        switch (category){
            case "XUV" : return true;
            case "SEDAN" : return true;
            case "HATCHBACK": return true;
            case "4*4" : return true;
            case "TRUCK" : return true;
            default:return false;
        }
    }
}
