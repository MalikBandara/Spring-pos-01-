package org.example.springboot01.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springboot01.entity.Customer;
import org.example.springboot01.entity.Item;

import java.util.Date;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderDTO {
    private int orderId;
    private String date;
    private int itemId;
    private double finalTotal;
    private int customerId;
    private double qty;
    private int price;

}
