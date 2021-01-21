package games.lab4.services;

import games.lab4.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;

public interface UserService extends UserDetailsService {


    void saveUser(User userForm) throws MessagingException;
}
