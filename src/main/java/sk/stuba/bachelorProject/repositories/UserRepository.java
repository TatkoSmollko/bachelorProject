package sk.stuba.bachelorProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.bachelorProject.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String userName);
}
