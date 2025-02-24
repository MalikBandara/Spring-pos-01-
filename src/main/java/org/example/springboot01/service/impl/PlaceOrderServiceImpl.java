package org.example.springboot01.service.impl;


import jakarta.transaction.Transactional;
import org.example.springboot01.dto.OrderDTO;
import org.example.springboot01.dto.OrderDetailsDTO;
import org.example.springboot01.entity.Customer;
import org.example.springboot01.entity.Item;
import org.example.springboot01.entity.OrderDetails;
import org.example.springboot01.entity.Orders;
import org.example.springboot01.repo.CustomerRepo;
import org.example.springboot01.repo.ItemRepo;
import org.example.springboot01.repo.OrderDetailRepo;
import org.example.springboot01.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceOrderServiceImpl {


    @Autowired
    private OrderRepo orderRepo;


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ItemRepo itemRepo;


    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;


    @Transactional
    public void placeOrder(OrderDTO orderDTO) {
        // 1. Convert OrderDTO to Orders entity
        Orders orders = new Orders();
        orders.setDate(orderDTO.getDate());
        Customer customer = customerRepo.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + orderDTO.getCustomerId()));

        orders.setCustomer(customer);

        orders.setTotal(orderDTO.getTotal());

        // 2. Save Orders first and get the persisted instance
        Orders savedOrder = orderRepo.save(orders);


        // 3. Process OrderDetails
        List<OrderDetailsDTO> orderDetailsList = orderDTO.getOrderDetailsList();

        for (OrderDetailsDTO orderDetailsDTO : orderDetailsList) {
            // 4. Fetch the Item entity
            Item item = itemRepo.findById(orderDetailsDTO.getItem())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + orderDetailsDTO.getItem()));

            // 5. Create a new OrderDetails entity
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setItem(item);
            orderDetails.setOrders(savedOrder);
            orderDetails.setQty(orderDetailsDTO.getQty());
            orderDetails.setUnitPrice(orderDetailsDTO.getUnitPrice());

            // 6. Save OrderDetails
            orderDetailRepo.save(orderDetails);
        }
    }


}
