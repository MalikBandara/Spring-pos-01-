package org.example.springboot01.controller;


import org.example.springboot01.dto.OrderDTO;
import org.example.springboot01.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/order")
public class OrderController {


    @Autowired
    private OrderServiceImpl orderService;


    @PostMapping("save")
    private boolean placeOrder(@RequestBody OrderDTO orderDTO){
        boolean b = orderService.placeOrder(orderDTO);
        return b;
    }
}

