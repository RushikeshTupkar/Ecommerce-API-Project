package com.example.Test4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AddressTable {
    @Id
    private Integer address_id;
    private String name;
    private String landmark;
    private String phone_number;
    private String zip_code;
    private String state;
    @JoinColumn(referencedColumnName = "user_id")
    @ManyToOne
    private User user;
}
