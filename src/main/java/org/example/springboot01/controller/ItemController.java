package org.example.springboot01.controller;


import org.example.springboot01.dto.ItemDto;
import org.example.springboot01.service.impl.ItemServiceImpl;
import org.example.springboot01.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/item")
public class ItemController {


    @Autowired
    private ItemServiceImpl itemService;


    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("save")
    private ResponseUtil saveItem(@RequestBody ItemDto itemDto){
        itemService.saveItem(itemDto);
        return  new ResponseUtil(200 , "Item save successfully ",null );
    }


    @DeleteMapping("delete/{id}")
    private ResponseUtil deleteItem(@PathVariable Integer id ){
         itemService.deleteItem(id);
         return  new ResponseUtil(200 , "Item Delete successfully" , null);

    }


    @PutMapping("update")
    private ResponseUtil updateItem(@RequestBody ItemDto itemDto){
         itemService.updateItem(itemDto);
         return  new ResponseUtil(200 , "Item update successfully" , null );
    }

    @GetMapping("getAll")
    private ResponseUtil getAllItem(){
        return  new ResponseUtil(200 , "item load" ,  itemService.getAllCustomer());

    }

}
