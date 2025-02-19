package org.example.springboot01.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders") // Avoids MySQL reserved keyword conflict
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private int orderId;
    private String date;
    private double finalTotal;
    private double qty;
    private int price;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")  // Matches "id" in Customer entity
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")  // Matches "id" in Customer entity
    private Item item;



}
