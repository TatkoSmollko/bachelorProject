package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.bachelorProject.model.Chimney;

@Repository
public interface ChimneyRepository extends JpaRepository<Chimney,String> {
}
