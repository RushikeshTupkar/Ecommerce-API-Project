package com.example.Test4.repository;

import com.example.Test4.model.AddressTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<AddressTable,Integer> {

}
