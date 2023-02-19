package com.example.Test4.service;

import com.example.Test4.model.User;
import com.example.Test4.repository.IUserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository UserRepository;

    public User setUser(JSONObject jsonObject) {
        User newUser = new User();
        newUser.setUser_id(jsonObject.getInt("user_id"));
        newUser.setName(jsonObject.getString("name"));
        newUser.setEmail(jsonObject.getString("email"));
        newUser.setPassword(jsonObject.getString("password"));
        newUser.setPhone_number(jsonObject.getString("phone_number"));
        return newUser;
    }

    public void saveUser(User newUser) {
        UserRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    public User getUserById(Integer id) {
        return UserRepository.findById(id).get();
    }

    public void deleteUserWithId(Integer userId) {
        UserRepository.deleteById(userId);
    }
}
