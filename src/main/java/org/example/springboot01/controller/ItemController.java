package org.example.springboot01.controller;


import org.example.springboot01.dto.ItemDto;
import org.example.springboot01.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/item")
public class ItemController {


    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping("save")
    private boolean saveItem(@RequestBody ItemDto itemDto){
        boolean b = itemService.saveItem(itemDto);
        return b;
    }


    @DeleteMapping("delete/{id}")
    private boolean deleteItem(@PathVariable Integer id ){
        boolean b = itemService.deleteItem(id);
        return b;
    }


    @PutMapping("update")
    private boolean updateItem(@RequestBody ItemDto itemDto){
        boolean b = itemService.updateItem(itemDto);
        return b;
    }

    @GetMapping("getAll")
    private List<ItemDto> getAllItem(){
        List<ItemDto> allItem = itemService.getAllCustomer();
        return allItem;

    }

}
