package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Item;
import sk.stuba.bachelorProject.model.Store;
import sk.stuba.bachelorProject.repositories.ItemRepository;
import sk.stuba.bachelorProject.repositories.StoreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ItemRepository itemRepository;

    public Store createStore() {
        String[] itemNames = {"Rohová lišta", "Fatrafol", "Okapový plech", "Šróby", "Tmel"};
        Store store =  storeRepository.save(new Store());
        for(String name : itemNames){
            itemRepository.save(new Item(store,0,0.0,2.5,name));
        }
        return store;
    }

    public Store getStoreById(String id) {
        return storeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Store"));
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store updateStore(String id, Store store) {
        Store storeToUpdate = storeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Store"));
        storeToUpdate.setItems(store.getItems());
        return storeRepository.save(storeToUpdate);
    }

    public void deleteStore(String id) {
        Store storeToDelete = storeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Store"));
        storeRepository.delete(storeToDelete);
    }

}
