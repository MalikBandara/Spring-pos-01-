package org.example.springboot01.repo;

import org.example.springboot01.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepo extends JpaRepository<Customer ,Integer> {

}
