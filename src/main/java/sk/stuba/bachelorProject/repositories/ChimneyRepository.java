package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.bachelorProject.model.Chimney;

public interface ChimneyRepository extends JpaRepository<Chimney,String> {
}
