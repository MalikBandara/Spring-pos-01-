package org.example.springboot01.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class OrderDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "itemcode")
    private Item item;

    private int qty ;

    private double unitPrice;




}
