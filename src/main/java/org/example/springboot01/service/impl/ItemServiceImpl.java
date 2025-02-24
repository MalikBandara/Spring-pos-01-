package org.example.springboot01.service.impl;


import org.example.springboot01.dto.ItemDto;
import org.example.springboot01.entity.Item;
import org.example.springboot01.repo.ItemRepo;
import org.example.springboot01.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    public ItemRepo itemRepo;


    @Autowired
    private ModelMapper modelMapper;
    public void saveItem(ItemDto itemDto) {

        if (itemRepo.existsById(itemDto.getId())){
            throw  new RuntimeException("item already exist");
        }
//        Item item = new Item(itemDto.getId(), itemDto.getName(), itemDto.getPrice(), itemDto.getQty());
        itemRepo.save(modelMapper.map(itemDto , Item.class));

    }



    public void deleteItem(Integer id) {
        if (itemRepo.existsById(id)) { // Check if customer exists
            itemRepo.deleteById(id);   // Delete by ID

        }

    }

    public void updateItem(ItemDto itemDto) {

        if (itemRepo.existsById(itemDto.getId())){
            itemRepo.save(modelMapper.map(itemDto, Item.class));
        }
        throw new RuntimeException("item does not exist ");

    }


    public List<ItemDto> getAllCustomer() {
        return modelMapper.map(itemRepo.findAll(), new TypeToken<List<ItemDto>>(){}.getType());
    }
}
