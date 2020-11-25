package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.bachelorProject.model.Attic;

public interface AtticRepository extends JpaRepository<Attic,String> {
}
