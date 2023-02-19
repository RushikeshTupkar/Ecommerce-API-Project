package com.example.Test4.controller;

import com.example.Test4.model.User;
import com.example.Test4.service.UserService;
import com.example.Test4.util.UserValidation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserValidation userValidation;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody String user){
        JSONObject json = new JSONObject(user);
        if(userValidation.checkUserIsPresentOrNot(json.getInt("user_id"))){
         return new ResponseEntity<>("user already exists with user_id - "+json.getString("user_id"),HttpStatus.BAD_REQUEST);
        }

        List<String>errors = userValidation.validDAta(json);
        if(errors.isEmpty()){
            User newUser = userService.setUser(json);
            userService.saveUser(newUser);
            return new ResponseEntity<>("User saved with user_id - "+newUser.getUser_id(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("These parameters should be valid - "+errors.toString(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/getUser_By_Id/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

         @PutMapping("/updateUser_details")
        public ResponseEntity<String> updateUserDetails(@RequestBody String data){
             JSONObject json = new JSONObject(data);
             if(userValidation.checkUserIsPresentOrNot(json.getInt("user_id"))){
                 List<String>errors = userValidation.validDAta(json);
                 if(errors.isEmpty()){
                     User newUser = userService.setUser(json);
                     userService.saveUser(newUser);
                     return new ResponseEntity<>("User updated with user_id - "+newUser.getUser_id(), HttpStatus.CREATED);
                 }else{
                     return new ResponseEntity<>("These parameters should be valid - "+errors.toString(),HttpStatus.BAD_REQUEST);
                 }
             }else{
                 return new ResponseEntity<>("No user found with user_id - "+json.getInt("user_id"),HttpStatus.BAD_REQUEST );
             }
        }

        @DeleteMapping("/deleteUser_with_user_id")
    public ResponseEntity<String> deleteUser(@RequestParam Integer user_id){
            if(!userValidation.checkUserIsPresentOrNot(user_id)){
                return new ResponseEntity<>("No user found with user_id - "+user_id,HttpStatus.BAD_REQUEST );
            }else{
                userService.deleteUserWithId(user_id);
                return new ResponseEntity<>("User deleted having user_id - "+user_id,HttpStatus.OK);
            }
        }

}
