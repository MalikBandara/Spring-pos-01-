package org.example.springboot01.repo;

import org.example.springboot01.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetails, Integer> {
}
