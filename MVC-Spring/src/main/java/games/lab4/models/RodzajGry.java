package games.lab4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;


@Entity
@Table(name = "RodzajGry")
@Getter @Setter
public class RodzajGry {
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

public RodzajGry(){

    }

    public RodzajGry(int id, String name) {
        this.id = id;
        this.name = name;

    }


}
