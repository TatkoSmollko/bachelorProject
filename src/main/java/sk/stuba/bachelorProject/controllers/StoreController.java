package sk.stuba.bachelorProject.controllers;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.Store;
import sk.stuba.bachelorProject.services.StoreService;

import java.util.List;

@RestController
@RequestMapping("/api/store/")
public class StoreController {
    @Autowired
    StoreService storeService;

    @PostMapping("/createStore")
    private Store createEmptyStore() {
        return storeService.createStore();
    }

    @GetMapping("/getStoreById/{id}")
    public Store getStoreById(@PathVariable(name = "id") String id) {
        return storeService.getStoreById(id);
    }

    @GetMapping("/getAllStores")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @PutMapping("/updateStore/{id}")
    public Store updateStore(@PathVariable(name = "id") String id, Store store) {
        return storeService.updateStore(id, store);
    }

    @DeleteMapping("deleteStore/{id}")
    public void deleteStore(@PathVariable(name = "id") String id) {
        storeService.deleteStore(id);
    }
}
