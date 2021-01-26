package games.lab4.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter @Setter
public class Koszyk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUser;

    @ManyToMany
    private List<Game> game;

    private Integer amount;
    @NumberFormat(pattern="#.00")
    private Float price;






}
