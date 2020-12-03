package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.bachelorProject.model.Attic;

@Repository
public interface AtticRepository extends JpaRepository<Attic,String> {

}
