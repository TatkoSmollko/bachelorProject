package sk.stuba.bachelorProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.stuba.bachelorProject.model.Store;
import sk.stuba.bachelorProject.services.StoreService;

@RestController
public class StoreController {
    @Autowired
    StoreService storeService;

    @PostMapping("/createStore")
    private Store createEmptyStore(){
        return storeService.createStore();
    }
}
