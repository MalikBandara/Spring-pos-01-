package org.example.springboot01.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springboot01.entity.Item;
import org.example.springboot01.entity.Orders;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailsDTO {
    private int id ;
    private int orders;
    private int item;
    private int qty ;
    private double unitPrice;
}
