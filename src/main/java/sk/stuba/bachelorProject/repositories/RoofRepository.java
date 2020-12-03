package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.bachelorProject.model.Roof;

@Repository
public interface RoofRepository extends JpaRepository<Roof,String> {
}
