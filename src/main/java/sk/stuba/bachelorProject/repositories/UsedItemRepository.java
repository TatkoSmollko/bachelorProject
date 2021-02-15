package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.bachelorProject.model.UsedItem;

public interface UsedItemRepository extends JpaRepository<UsedItem,String> {

}
