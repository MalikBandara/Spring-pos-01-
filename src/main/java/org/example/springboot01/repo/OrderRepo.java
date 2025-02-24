package org.example.springboot01.repo;

import org.example.springboot01.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepo extends JpaRepository<Orders , Integer> {
}
