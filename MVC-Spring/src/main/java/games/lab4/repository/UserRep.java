package games.lab4.repository;

import games.lab4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
