package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.bachelorProject.model.Item;

public interface ItemRepository extends JpaRepository<Item,String> {
}
