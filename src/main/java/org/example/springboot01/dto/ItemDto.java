package org.example.springboot01.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDto {

    private int id ;

    private String name;

    private double price;

    private int qty ;

}
