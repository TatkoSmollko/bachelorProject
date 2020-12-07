package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Item;
import sk.stuba.bachelorProject.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemById(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
    }

    public Item updateItem(String id, Item item) {
        Item itemToUpdate = itemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
        itemToUpdate.setCount(item.getCount());
        itemToUpdate.setPrice(item.getPrice());
        itemToUpdate.setSize(item.getSize());
        itemToUpdate.setStores(item.getStores());
        return itemRepository.save(itemToUpdate);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void deleteItem(String id) {
        Item itemToDelete = itemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id", id));
        itemRepository.delete(itemToDelete);
    }

}
