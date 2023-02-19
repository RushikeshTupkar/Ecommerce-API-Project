package com.example.Test4.util;

import com.example.Test4.model.AddressTable;
import com.example.Test4.model.OrderTable;
import com.example.Test4.model.Product;
import com.example.Test4.model.User;
import com.example.Test4.repository.IAddressRepository;
import com.example.Test4.repository.IOrderTableRepository;
import com.example.Test4.repository.IProductRepository;
import com.example.Test4.repository.IUserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderValidation {
    @Autowired
    IOrderTableRepository OrderTableRepository;

    @Autowired
    IAddressRepository AddressRepository;

    @Autowired
    IProductRepository ProductRepository;

    @Autowired
    IUserRepository UserRepository;
    public List<String> validateOrder(JSONObject json) {
        List<String>errors = new ArrayList<>();
        Integer user_id = json.getInt("user_id");
        User u1 = UserRepository.findById(user_id).get();
        Integer product_id = json.getInt("product_id");
        Product p1 = ProductRepository.findById(product_id).get();
        Integer address_id = json.getInt("address_id");
        AddressTable ad1 = AddressRepository.findById(address_id).get();
        if(u1==null){
            errors.add("user_id : User does not exists");
        }
        if(p1==null){
            errors.add("product_id : Product does not exists");
        }
        if(ad1==null){
            errors.add("address_id : Address does not exists");
        }
        if(ad1.getUser().getUser_id()!=user_id){
            errors.add("address_id : Address is not associated with user having user_id - "+ user_id);
        }
        return errors;
    }

    public  boolean checkOrderIsPresentOrNot(Integer id){
        List<Integer> list = new ArrayList<>();
        list.add(id);
        List<OrderTable>userList = new ArrayList<>();
        userList = (OrderTableRepository.findAllById(list));
        if(!userList.isEmpty()){
            return true;
        }else{
            return false;
        }
    }



}
