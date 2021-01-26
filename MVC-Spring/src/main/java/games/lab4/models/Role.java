package games.lab4.models;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Service;


import javax.persistence.*;

import java.util.Set;


@Entity
@Getter
@Setter
@Table(name="roles")
public class Role {



    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
     private Integer id;

    @Enumerated(EnumType.STRING)
    private Types type;
    @ManyToMany(mappedBy= "roles")
    private Set<User> users;

public Role(Types type) {
    this.type=type;
}

    public Role() {

    }

    public static enum Types{
    ROLE_ADMIN,
    ROLE_USER,
        ROLE_VIP
}



}
