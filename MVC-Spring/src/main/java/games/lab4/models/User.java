package games.lab4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Email
    private String email;
    private String password;

    // dane osobowe

    private String imie;
    private String nazwisko;
    private String miasto;
    private String ulica;
    private Integer nrDomu;

//




    @Transient
    private  String passwordConfirm;
    private boolean enabled= false;

    private String activationCode;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="Koszyk")
    private Koszyk koszyk;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="MyOrders")
    private OrderShop order;

@AssertTrue
private boolean isPasswordEquals(){
    return password==null|| passwordConfirm==null || password.equals(passwordConfirm);
}
    @ManyToMany
    @JoinTable(name="users_roles", joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name= "role_id"))
    private Set<Role> roles;

 public User(String username)
 {
     this(username, false);
 }

    public User(String username, boolean enabled){
        this.username=username;
        this.enabled=enabled;
    }

    public User() {

    }


}
