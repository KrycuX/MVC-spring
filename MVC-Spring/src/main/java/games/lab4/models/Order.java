package games.lab4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Order {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    private float price;


    @ManyToOne
    private User user;
}
