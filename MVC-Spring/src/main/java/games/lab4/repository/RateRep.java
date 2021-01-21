package games.lab4.repository;

import games.lab4.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRep extends JpaRepository<Rate, Integer> {
}
