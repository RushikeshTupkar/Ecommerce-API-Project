package com.example.Test4.controller;

import com.example.Test4.model.OrderTable;
import com.example.Test4.service.OrderService;
import com.example.Test4.util.OrderValidation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderValidation orderValidation;

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody String order){
        JSONObject json = new JSONObject(order);
        boolean isOrderAlreadyPresent = orderValidation.checkOrderIsPresentOrNot(json.getInt("order_id"));
        if(isOrderAlreadyPresent){
            return new ResponseEntity<>("Order already exists with order_id - "+json.getInt("order_id"),HttpStatus.BAD_REQUEST);
        }

        List<String> erors =  orderValidation.validateOrder(json);
        if(erors.isEmpty()){
            OrderTable o1 = orderService.setOrder(json);
        orderService.placeOrder(o1);
            return new ResponseEntity<>("Order validated and placed with order_id - "+json.getInt("order_id"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(erors.toString(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllOrders")
    public List<OrderTable> getAllorders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/find_order_by_order_id/{order_id}")
    public ResponseEntity<String> getOrdersByOrderId(@PathVariable Integer order_id){
      if(orderValidation.checkOrderIsPresentOrNot(order_id)){
          return new ResponseEntity<>(orderService.getOrderByOrderId(order_id).toString(),HttpStatus.OK);
      }
        return new ResponseEntity<>("No order exists with order_id - "+order_id,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete_order_by_order_id/{order_id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer order_id){
        if(orderValidation.checkOrderIsPresentOrNot(order_id)){
            orderService.deleteOrderByOrderId(order_id);
            return  new ResponseEntity<>("Order deleted by order_id - "+order_id,HttpStatus.OK);
        }
        return new ResponseEntity<>("No order exists with order_id - "+order_id,HttpStatus.BAD_REQUEST);
    }
    }

