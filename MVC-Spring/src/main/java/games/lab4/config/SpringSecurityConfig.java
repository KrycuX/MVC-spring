package games.lab4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
   @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }
   @Bean
   @Profile(ProfilesNames.USERS_IN_MEMORY)
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
   {
       InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
       User.UserBuilder userBuilder = User.builder();

       UserDetails admin= userBuilder.username("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build();
       UserDetails user= userBuilder.username("user").password(passwordEncoder.encode("user")).roles("USER").build();
       UserDetails vip= userBuilder.username("vip").password(passwordEncoder.encode("vip123")).roles("VIP").build();
       manager.createUser(admin);
       manager.createUser(user);
       manager.createUser(vip);

       return manager;
   }

   @Override
    protected void configure(HttpSecurity http) throws Exception
   {
http.authorizeRequests()
        .antMatchers("/webjars/**", "/css/**","/","/game/list","/user/registration**","/user/registrationConf","/game/show**","/images/**").permitAll()
        .antMatchers("/game/show","/game/").hasRole("USER")
        .antMatchers("/game/add", "/game/edit**","/game/delete**","/game/listEdit/**").hasRole("ADMIN")
        .antMatchers("/game/edit").hasRole("VIP")
        .anyRequest().authenticated();
 http.formLogin()
         .loginPage("/game/loginPanel").permitAll()
         .loginProcessingUrl("/login")
         .failureUrl("/game/loginPanel?error=true")
         .defaultSuccessUrl("/");

http.exceptionHandling().accessDeniedHandler(createDeniedHandler());
   }

    private AccessDeniedHandler createDeniedHandler() {
       AccessDeniedHandlerImpl impl= new AccessDeniedHandlerImpl();
       impl.setErrorPage("/error403");
       return impl;

       }



}
