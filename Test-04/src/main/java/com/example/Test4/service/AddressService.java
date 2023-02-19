package com.example.Test4.service;

import com.example.Test4.model.AddressTable;
import com.example.Test4.model.User;
import com.example.Test4.repository.IAddressRepository;
import com.example.Test4.repository.IUserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    IAddressRepository AddressRepository;

    @Autowired
    IUserRepository UserRepository;

    public void addAddress(AddressTable address) {
        AddressRepository.save(address);
    }

    public AddressTable setAddress(JSONObject json) {
        AddressTable addressTable = new AddressTable();
        addressTable.setAddress_id(json.getInt("address_id"));
        addressTable.setName(json.getString("name"));
        addressTable.setLandmark(json.getString("landmark"));
        addressTable.setPhone_number(json.getString("phone_number"));
        addressTable.setZip_code(json.getString("zip_code"));
        addressTable.setState(json.getString("state"));
        User user = UserRepository.findById(json.getInt("user_id")).get();
        addressTable.setUser(user);
        return addressTable;
    }

    public List<AddressTable> getAllAddresses() {
        return AddressRepository.findAll();
    }

    public void deleteAddressByAddress_id(Integer id) {
        AddressRepository.deleteById(id);
    }

    public AddressTable getAddressByAddress_id(Integer id) {
        return AddressRepository.findById(id).get();
    }

    public JSONArray getAddresses(Integer id) {
        JSONArray array = new JSONArray();
        List<AddressTable> allAddresses = AddressRepository.findAll();
        for(AddressTable ad1 : allAddresses){
            if(ad1.getUser().getUser_id()==id){
                JSONObject j1 = new JSONObject();
                j1.put("address_id",ad1.getAddress_id());
                j1.put("name",ad1.getName());
                j1.put("landmark",ad1.getLandmark());
                j1.put("phone_number",ad1.getPhone_number());
                j1.put("zip_code",ad1.getZip_code());
                j1.put("state",ad1.getState());
                j1.put("user_id",ad1.getUser().getUser_id());
                array.put(j1);
            }
        }
        return array;
    }
}
