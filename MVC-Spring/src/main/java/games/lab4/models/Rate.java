package games.lab4.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
@Getter @Setter
public class Rate {
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ocena;

    public Rate() {


    }




    public Rate(int id, String ocena) {
        this.id=id;
        this.ocena=ocena;
    }


}
