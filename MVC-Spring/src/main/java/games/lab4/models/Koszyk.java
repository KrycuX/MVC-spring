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
    private Integer id;

    private Long userId;

    @ManyToMany
    private List<Game> listGame;

    @NumberFormat(pattern="#.00")
    @NotNull
    private Float price;



}
