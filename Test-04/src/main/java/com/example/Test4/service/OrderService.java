package com.example.Test4.service;

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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    IOrderTableRepository OrderTableRepository;

    @Autowired
    IAddressRepository AddressRepository;

    @Autowired
    IProductRepository ProductRepository;

    @Autowired
    IUserRepository UserRepository;

    public OrderTable setOrder(JSONObject json) {
        OrderTable o1 = new OrderTable();
        o1.setOrder_id(json.getInt("order_id"));


        o1.setOrder_quantity(json.getInt("order_quantity"));
        User u1 = UserRepository.findById(json.getInt("user_id")).get();
        o1.setUser(u1);
        Product p1 = ProductRepository.findById(json.getInt("product_id")).get();
        o1.setProduct(p1);
        AddressTable ad1 = AddressRepository.findById(json.getInt("address_id")).get();
        o1.setAddress(ad1);
        return o1;
    }

    public List<OrderTable> getAllOrders() {
        return OrderTableRepository.findAll();
    }

    public void placeOrder(OrderTable o1) {
        OrderTableRepository.save(o1);
    }

    public JSONObject getOrderByOrderId(Integer orderId) {
        OrderTable o1 = OrderTableRepository.findById(orderId).get();
        JSONObject order = new JSONObject();
        order.put("order_id",o1.getOrder_id());
        order.put("order_quantity",o1.getOrder_quantity());
        order.put("user_id",o1.getUser().getUser_id());
        order.put("product_id",o1.getProduct().getProduct_id());
        order.put("address_id",o1.getAddress().getAddress_id());
        return order;
//        return OrderTableRepository.findById(orderId).get();



    }

    public void deleteOrderByOrderId(Integer orderId) {
        OrderTableRepository.deleteById(orderId);
    }
}
