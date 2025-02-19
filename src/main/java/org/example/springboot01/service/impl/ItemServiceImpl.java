package org.example.springboot01.service.impl;


import org.example.springboot01.dto.ItemDto;
import org.example.springboot01.entity.Item;
import org.example.springboot01.repo.ItemRepo;
import org.example.springboot01.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    public ItemRepo itemRepo;
    public boolean saveItem(ItemDto itemDto) {

        Item item = new Item(itemDto.getId(), itemDto.getName(), itemDto.getPrice(), itemDto.getQty());
        itemRepo.save(item);
        return true;
    }



    public boolean deleteItem(Integer id) {
        if (itemRepo.existsById(id)) { // Check if customer exists
            itemRepo.deleteById(id);   // Delete by ID
            return true;
        }
        return false; // Return false if customer doesn't exist
    }

    public boolean updateItem(ItemDto itemDto) {
        Optional<Item> existingItem = itemRepo.findById(itemDto.getId());

        if (existingItem.isPresent()){
            Item item = existingItem.get();

            item.setName(itemDto.getName());
            item.setPrice(itemDto.getPrice());
            item.setQty(itemDto.getQty());

            itemRepo.save(item);
            return true;
        }
        return false;

    }


    public List<ItemDto> getAllCustomer() {
        List<Item> all = itemRepo.findAll();
        List<ItemDto> itedto = new ArrayList<>();

        for (Item item : all) {
            ItemDto dto = new ItemDto(item.getId(), item.getName(),item.getPrice(), item.getQty());
            itedto.add(dto);
        }

        return itedto;
    }
}
