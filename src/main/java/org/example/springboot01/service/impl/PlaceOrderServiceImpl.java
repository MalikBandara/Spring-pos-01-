package org.example.springboot01.service.impl;


import jakarta.transaction.Transactional;
import org.example.springboot01.dto.ItemDto;
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
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Orders orders = new Orders();
        orders.setDate(orderDTO.getDate());
        Customer customer = customerRepo.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + orderDTO.getCustomerId()));

        orders.setCustomer(customer);

        orders.setTotal(orderDTO.getTotal());


        Orders savedOrder = orderRepo.save(orders);



        List<OrderDetailsDTO> orderDetailsList = orderDTO.getOrderDetailsList();

        for (OrderDetailsDTO orderDetailsDTO : orderDetailsList) {

            Item item = itemRepo.findById(orderDetailsDTO.getItem())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + orderDetailsDTO.getItem()));


            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setItem(item);
            orderDetails.setOrders(savedOrder);
            orderDetails.setQty(orderDetailsDTO.getQty());
            orderDetails.setUnitPrice(orderDetailsDTO.getUnitPrice());


            orderDetailRepo.save(orderDetails);

            itemRepo.updateQty(item.getId(), orderDetailsDTO.getQty());
        }
    }


    public List<OrderDTO> getAll() {
        return mapper.map(orderRepo.findAll(), new TypeToken<List<OrderDTO>>(){}.getType());

    }

    public List<OrderDetailsDTO> getAllDetails() {
        return orderDetailRepo.findAll().stream().map(orderDetail -> {
            OrderDetailsDTO dto = new OrderDetailsDTO();
            dto.setId(orderDetail.getId());
            dto.setOrders(orderDetail.getOrders().getOrderId());
            dto.setItem(orderDetail.getItem().getId()); // Extract itemId instead of entire Item object
            dto.setQty(orderDetail.getQty());
            dto.setUnitPrice(orderDetail.getUnitPrice());
            return dto;
        }).collect(Collectors.toList());
    }

}
