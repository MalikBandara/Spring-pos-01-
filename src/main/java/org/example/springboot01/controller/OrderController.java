package org.example.springboot01.controller;


import org.example.springboot01.dto.OrderDTO;
import org.example.springboot01.service.impl.PlaceOrderServiceImpl;
import org.example.springboot01.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/Order")
public class OrderController {



    @Autowired
    private PlaceOrderServiceImpl placeOrderService;


    @PostMapping("save")
    public ResponseUtil saveOrder(@RequestBody OrderDTO orderDTO){

            placeOrderService.placeOrder(orderDTO);
            return new ResponseUtil(200, "place order " , null);
    }
}
