package org.example.springboot01.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String date;

    private double total;


    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "orders")
    private List<OrderDetails> orderDetailsList;




}

