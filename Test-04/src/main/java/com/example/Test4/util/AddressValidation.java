package com.example.Test4.util;

import com.example.Test4.model.AddressTable;
import com.example.Test4.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressValidation {

    @Autowired
    IAddressRepository AddressRepository;
    public  boolean checkAddressIsPresentOrNot(Integer id){
        List<Integer> list = new ArrayList<>();
        list.add(id);
        List<AddressTable>userList = new ArrayList<>();
        userList = (AddressRepository.findAllById(list));
        if(!userList.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
