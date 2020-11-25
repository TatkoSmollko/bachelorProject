package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.bachelorProject.model.Roof;

public interface RoofRepository extends JpaRepository<Roof,String> {
}
