package com.example.Test4.controller;

import com.example.Test4.model.AddressTable;
import com.example.Test4.service.AddressService;
import com.example.Test4.util.AddressValidation;
import com.example.Test4.util.ProductValidation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    ProductValidation productValidation;

    @Autowired
    AddressValidation addressValidation;

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody String newAddress){
        JSONObject json = new JSONObject(newAddress);
        Integer user_id = json.getInt("user_id");
        if(addressValidation.checkAddressIsPresentOrNot(json.getInt("address_id"))){
            return new ResponseEntity<>("Address already exists with address_id - "+json.getInt("address_id"),HttpStatus.BAD_REQUEST);
        }
        if(productValidation.checkProductIsPresentOrNot(user_id)){
            AddressTable ad1 = addressService.setAddress(json);
            addressService.addAddress(ad1);
            return new ResponseEntity<>("Address saved having address_id - "+ad1.getAddress_id()+" , user_id - "
                    +user_id,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No user found with user_id - "+user_id, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getALl_addresses")
        public List<AddressTable> getAllAddresses(){
            return addressService.getAllAddresses();
        }

        @DeleteMapping("delete_addredd_by_address_id/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id){
        if(addressValidation.checkAddressIsPresentOrNot(id)){
            addressService.deleteAddressByAddress_id(id);
            return new ResponseEntity<>("Address deleted having address_id - "+id,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No address found with address_id - "+id,HttpStatus.BAD_REQUEST);
        }
        }

        @GetMapping("getAddres_By_address_id/{id}")
    public ResponseEntity<AddressTable> findAddressById(@PathVariable Integer id){
            if(addressValidation.checkAddressIsPresentOrNot(id)){
                return new ResponseEntity<>(addressService.getAddressByAddress_id(id),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
        }

        @GetMapping("get_all_addresses_of_user/{id}")
        public ResponseEntity<String> getAddresses(@PathVariable Integer id){
            JSONArray array = addressService.getAddresses(id);
            if(array.length()==0){
                return new ResponseEntity<>("No address with user_id - "+id,HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(array.toString(),HttpStatus.OK);
        }

}
