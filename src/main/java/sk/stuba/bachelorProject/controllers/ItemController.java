package sk.stuba.bachelorProject.controllers;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.Item;
import sk.stuba.bachelorProject.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/item/")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping("createItem")
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @GetMapping("getItemById/{id}")
    public Item getItemById(@PathVariable(name = "id") String id) {
        return itemService.getItemById(id);
    }

    @PutMapping("updateItem/{id}")
    public Item updateItem(String id, Item item) {
        return itemService.updateItem(id, item);
    }

    @GetMapping("getAllItems")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @DeleteMapping("deleteItem/{id}")
    public void deleteItem(@PathVariable(name = "id") String id) {
        itemService.deleteItem(id);
    }
}
