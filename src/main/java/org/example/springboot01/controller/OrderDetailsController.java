package org.example.springboot01.controller;


import org.example.springboot01.service.impl.PlaceOrderServiceImpl;
import org.example.springboot01.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/OrderDetail")
public class OrderDetailsController {

    @Autowired
    private PlaceOrderServiceImpl placeOrderService;

    @GetMapping("getAll")
    public ResponseUtil getAll(){

        return new ResponseUtil(200, "Load order " , placeOrderService.getAllDetails());
    }
}
