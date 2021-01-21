package games.lab4.models;

import lombok.Data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="genres")
@Data
public class GameGenre {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy="genres", fetch= FetchType.EAGER)
    private Set<Game> games;
    public GameGenre(){
        games= new HashSet<>();
    }
public GameGenre(String name){

        this.name=name;
}
}
