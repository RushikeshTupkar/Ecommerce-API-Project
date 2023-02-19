package com.example.Test4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderTable {
    @Id
    private Integer order_id;
    private Integer order_quantity;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
        User user;
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName="product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "address_id",referencedColumnName = "address_id")
    AddressTable address;


}
