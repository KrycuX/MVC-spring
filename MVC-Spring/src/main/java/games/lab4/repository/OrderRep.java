package games.lab4.repository;

import games.lab4.models.OrderShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRep extends JpaRepository<OrderShop, Long> {
}
