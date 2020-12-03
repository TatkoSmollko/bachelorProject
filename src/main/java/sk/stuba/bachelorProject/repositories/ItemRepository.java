package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.bachelorProject.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {
}
