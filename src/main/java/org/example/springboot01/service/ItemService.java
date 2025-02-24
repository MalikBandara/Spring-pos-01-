package org.example.springboot01.service;

import org.example.springboot01.dto.ItemDto;
import org.example.springboot01.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    public void saveItem(ItemDto itemDto) ;

    public void deleteItem(Integer id) ;

    public void updateItem(ItemDto itemDto) ;


    public List<ItemDto> getAllCustomer() ;
}
