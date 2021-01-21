package games.lab4.repository;

import games.lab4.models.GameGenre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameGenreRep extends JpaRepository<GameGenre, Integer> {
}
