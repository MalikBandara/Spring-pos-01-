package org.example.springboot01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.springboot01.entity.OrderDetails;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private int orderId;
    private String date;
    private double total;
    private int customerId;
    private List<OrderDetailsDTO> orderDetailsList;
}
