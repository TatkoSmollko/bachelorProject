package sk.stuba.bachelorProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Item;
import sk.stuba.bachelorProject.model.Store;
import sk.stuba.bachelorProject.repositories.StoreRepository;

import java.util.ArrayList;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;
    public Store createStore(){
        return storeRepository.save(new Store(new ArrayList<Item>()) );
    }
}
