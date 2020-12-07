package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Item;
import sk.stuba.bachelorProject.model.Store;
import sk.stuba.bachelorProject.repositories.StoreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    public Store createStore() {
        return storeRepository.save(new Store());
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
