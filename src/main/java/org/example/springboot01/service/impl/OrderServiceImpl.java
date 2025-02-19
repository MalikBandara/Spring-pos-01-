package org.example.springboot01.service.impl;


import jakarta.transaction.Transactional;
import org.example.springboot01.dto.OrderDTO;
import org.example.springboot01.entity.Customer;
import org.example.springboot01.entity.Item;
import org.example.springboot01.entity.Order;
import org.example.springboot01.repo.CustomerRepo;
import org.example.springboot01.repo.ItemRepo;
import org.example.springboot01.repo.OrderRepo;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl {


    @Autowired
    private OrderRepo orderRepo;


    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Transactional
    public boolean placeOrder(OrderDTO orderDTO) {

        Optional<Customer> customerOptional = customerRepo.findById(orderDTO.getCustomerId());

        Optional<Item> itemOptional = itemRepo.findById(orderDTO.getItemId());

        if (customerOptional.isEmpty()) {
            throw new RuntimeException("Customer not found with ID: " + orderDTO.getCustomerId());
        }
        else if (itemOptional.isEmpty()) {
            throw new RuntimeException("item not found with ID: " + orderDTO.getItemId());
        }

        int price = orderDTO.getPrice();
        double qty = orderDTO.getQty();

        double finalPrice = price*qty;

        Customer customer = customerOptional.get();
        Item item = itemOptional.get();
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setDate(orderDTO.getDate());
        order.setItem(item);
        order.setQty(orderDTO.getQty());
        order.setFinalTotal(finalPrice);
        order.setCustomer(customer);
        order.setPrice(orderDTO.getPrice());
        orderRepo.save(order);
        return true;
    }
}