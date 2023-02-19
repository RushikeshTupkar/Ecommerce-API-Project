package com.example.Test4.util;

import com.example.Test4.model.User;
import com.example.Test4.repository.IUserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidation {
    @Autowired
    IUserRepository UserRepository;

    public  boolean checkUserIsPresentOrNot(Integer id){
        List<Integer>list = new ArrayList<>();
        list.add(id);
        List<User>userList = new ArrayList<>();
        userList = (UserRepository.findAllById(list));
    if(!userList.isEmpty()){
        return true;
    }else{
        return false;
    }
    }



    public  List<String> validDAta(JSONObject json) {
        List<String>errors = new ArrayList<>();
//        List<Integer>list = new ArrayList<>();
//        list.add(json.getInt("user_id"));
//        List<User>userList = new ArrayList<>();
//        userList = (UserRepository.findAllById(list));
//        if(!userList.isEmpty()) {
//            errors.add("user already exists with user_id - "+json.getString("user_id"));
//            return errors;
//        }
            if(json.getString("user_id").length()==0){
         errors.add("user_id : user_id cannot be empty");
        }
        if(json.getString("name").length()==0){
            errors.add("name : name is mandatory");
        }
        if(json.getString("email").length()==0){
            errors.add("email : email is mandatory");
        }else if(!UserValidation.isValidEmail(json.getString("email"))){
            errors.add("email : Please provide valid email");
        }
        if(json.getString("password").length()==0){
            errors.add("password : password is mandatory");
        }
        if(json.getString("phone_number").length()<10){
            errors.add("phone_number : phone_number is mandatory having 10 digits");
        }else if(!UserValidation.isValidMobileNo(json.getString("phone_number"))){
            errors.add("phone_number : Please provide valid phone_number");
        }
        return errors;
    }


    public static boolean isValidEmail(String value){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        String out = ((matcher.matches() ? "valid" : "invalid"));
        return out.equals("valid");
    }

    public static boolean isValidMobileNo(String str)
    {
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");

        Matcher match = ptrn.matcher(str);

        return (match.find() && match.group().equals(str));
    }
}
