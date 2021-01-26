package games.lab4.services.implementation;

import games.lab4.config.ProfilesNames;
import games.lab4.models.Koszyk;
import games.lab4.models.Role;
import games.lab4.models.User;
import games.lab4.repository.RoleRep;
import games.lab4.repository.UserRep;
import games.lab4.services.EmailService;
import games.lab4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Profile(ProfilesNames.USERS_IN_DATABASE)
public class UserServiceImp implements UserService {

@Autowired
UserRep userRep;

@Autowired
RoleRep roleRep;
@Autowired
PasswordEncoder passwordEncoder;

@Autowired
private EmailService emailService;

@Autowired
private TemplateEngine templateEngine;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRep.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return convertToUserDetails(user);
    }

    private UserDetails convertToUserDetails(User user){
        var grantedAuthorities= user.getRoles().stream().map(r-> new SimpleGrantedAuthority(r.getType().toString())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public void saveUser(User userForm) throws MessagingException {
Role userRole= roleRep.findByType(Role.Types.ROLE_USER);
var roles= new HashSet<Role>();
roles.add(userRole);
userForm.setRoles(roles);
userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
userForm.setPasswordConfirm(userForm.getPassword());
userForm.setKoszyk(new Koszyk());
//userForm.setEnabled(false);
var activationKey= UUID.randomUUID().toString();
userForm.setActivationCode(activationKey);
String message= createMessage(activationKey,userForm.getUsername());
emailService.sendMimeMessage(userForm.getEmail(),"Activation",message);
     userRep.save(userForm);
    }

    private String createMessage(String activationKey,String name) {
        var thymleafCtx=new Context();
        thymleafCtx.setVariable("header","O to twój link aktywacyjny!");
        thymleafCtx.setVariable("title","kliknij tutaj aby aktywowac");
        thymleafCtx.setVariable("description","http://localhost:8080/user/registrationConf?token="+activationKey+"&userName="+name);
        String html=templateEngine.process("mail-temp/activation",thymleafCtx);


        return html;

    }


}
