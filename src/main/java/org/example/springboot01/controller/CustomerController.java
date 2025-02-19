package org.example.springboot01.controller;


import org.example.springboot01.dto.CustomerDto;
import org.example.springboot01.service.impl.CustomerServiceImpl;
import org.example.springboot01.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {


    @Autowired
    private CustomerServiceImpl customerService;


    @PostMapping("save")
    private ResponseUtil saveCus(@RequestBody CustomerDto customerDto){
        boolean b = customerService.saveCustomer(customerDto);
        if (b) {


            return new ResponseUtil(201, "Customer Saved !", null);
        }else {
            return new ResponseUtil(200 , "Exist Customer" , null);
        }
    }


    @DeleteMapping("delete/{id}")
    private boolean deleteCustomer(@PathVariable Integer id ){
        boolean b = customerService.deleteCustomer(id);
        return b;
    }


    @PutMapping("update")
    private boolean updateCustomer(@RequestBody CustomerDto customerDto){
        boolean b = customerService.updateCustomer(customerDto);
        return b;
    }


    @GetMapping("getAll")
    private List<CustomerDto> getAllCustomer(){
        List<CustomerDto> allCustomer = customerService.getAllCustomer();
        return allCustomer;

    }



}
