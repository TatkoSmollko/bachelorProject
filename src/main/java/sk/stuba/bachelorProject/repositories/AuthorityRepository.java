package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.bachelorProject.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
}
