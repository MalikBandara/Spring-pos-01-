package org.example.springboot01.service.impl;


import jakarta.transaction.Transactional;
import org.example.springboot01.dto.CustomerDto;
import org.example.springboot01.entity.Customer;
import org.example.springboot01.repo.CustomerRepo;
import org.example.springboot01.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;


    @Transactional
    public void saveCustomer(CustomerDto customerDto) {
//        Customer customer = new Customer(customerDto.getId(), customerDto.getName(), customerDto.getAddress());
        if (customerRepo.existsById(customerDto.getId())){
            throw  new RuntimeException("customer already exist");
        }
        customerRepo.save(modelMapper.map(customerDto, Customer.class));
    }



    public void deleteCustomer(Integer id) {
        if (customerRepo.existsById(id)) { // Check if customer exists
            customerRepo.deleteById(id);   // Delete by ID
        }

    }

    public boolean updateCustomer(CustomerDto customerDto) {
        Optional<Customer> existingCustomerOpt = customerRepo.findById(customerDto.getId());

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();

            // Update fields
//            existingCustomer.setName(customerDto.getName());
//            existingCustomer.setAddress(customerDto.getAddress());


            modelMapper.map(customerDto, existingCustomer);

            // Save the updated customer
            customerRepo.save(existingCustomer);

            return true; // Successfully updated
        }

        // Customer not found
        return false;
    }

    public List<CustomerDto> getAllCustomer() {
//        List<Customer> all = customerRepo.findAll();
//        List<CustomerDto> customerDtos = new ArrayList<>();
//
//        for (Customer customer : all) {
//            modelMapper.map(customer , CustomerDto.class);
//            CustomerDto dto = new CustomerDto(customer.getId(), customer.getName(),customer.getAddress());
//            customerDtos.add(dto);
//        }
//
//        return customerDtos;

        return  modelMapper.map(customerRepo.findAll(),new TypeToken<List<CustomerDto>>(){}.getType());
    }

}
