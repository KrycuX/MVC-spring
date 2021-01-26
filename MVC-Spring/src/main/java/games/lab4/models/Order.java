package games.lab4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Order {
    @Id
    private Long id;
    private Date orderDate;
    private int orderNum;
    private double amount;
    private float price;

    @ManyToOne
    private Game game;
    @ManyToOne
    private User user;
}
