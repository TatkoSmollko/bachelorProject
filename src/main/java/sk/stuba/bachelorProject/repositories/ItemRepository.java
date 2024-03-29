package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.bachelorProject.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    Item findByName(String name);

    List<Item> findByStoreId(String storeId);
}
