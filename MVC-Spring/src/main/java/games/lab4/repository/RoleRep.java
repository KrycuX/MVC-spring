package games.lab4.repository;

import games.lab4.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRep extends JpaRepository<Role, Integer> {
    Role findByType(Role.Types types);
}
