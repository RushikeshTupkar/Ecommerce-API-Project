package com.example.Test4.repository;

import com.example.Test4.model.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderTableRepository extends JpaRepository<OrderTable,Integer> {

}
