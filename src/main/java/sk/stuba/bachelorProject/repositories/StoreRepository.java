package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.bachelorProject.model.Store;

public interface StoreRepository extends JpaRepository<Store,String> {
}
