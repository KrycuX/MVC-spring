package games.lab4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private String password;

    @Transient
    private  String passwordConfirm;
    private boolean enabled= false;

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    private String activationCode;

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
