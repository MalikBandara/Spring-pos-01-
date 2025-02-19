package org.example.springboot01.service;

import org.example.springboot01.dto.ItemDto;
import org.example.springboot01.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    public boolean saveItem(ItemDto itemDto) ;

    public boolean deleteItem(Integer id) ;

    public boolean updateItem(ItemDto itemDto) ;


    public List<ItemDto> getAllCustomer() ;
}
