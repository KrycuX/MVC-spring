package games.lab4.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Dostawa {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String nazwa;
    @NumberFormat(pattern="#.00")
    private Float cena;
    public Dostawa(){

    }

    public Dostawa(String nazwa, Float cena){
        this.nazwa=nazwa;
        this.cena=cena;
    }
}
