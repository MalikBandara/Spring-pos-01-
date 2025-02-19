package org.example.springboot01.service;

import org.example.springboot01.dto.CustomerDto;
import org.example.springboot01.entity.Customer;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    boolean saveCustomer(CustomerDto customerDto) ;

    public boolean deleteCustomer(Integer id) ;

     boolean updateCustomer(CustomerDto customerDto) ;

     List<CustomerDto> getAllCustomer();


}
