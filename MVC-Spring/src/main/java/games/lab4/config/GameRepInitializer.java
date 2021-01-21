package games.lab4.config;

import games.lab4.baza;
import games.lab4.models.GameGenre;
import games.lab4.models.Role;
import games.lab4.models.User;
import games.lab4.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

@Configuration
public class GameRepInitializer {

    private GameRepository gameRepository;
    @Autowired
  public void setGameRepository(GameRepository gameRepository){
      this.gameRepository=gameRepository;

  }
    private RodzajGryRep rodzajGryRep;
    @Autowired
    public void setRodzajGryRep(RodzajGryRep  rodzajGryRep){
        this.rodzajGryRep=rodzajGryRep;

    }
    private RateRep rateRep;
    @Autowired
    public void setRateRep(RateRep rateRep){
        this.rateRep=rateRep;

    }
    private GameGenreRep gameGenreRep;
    @Autowired
    public void setGameGenreRep(GameGenreRep gameGenreRep){
        this.gameGenreRep=gameGenreRep;

    }
    private RoleRep roleRep;
    @Autowired
    public void setRoleRep(RoleRep roleRep){
        this.roleRep=roleRep;

    }
    private UserRep userRep;
    @Autowired
    public void setUserRep(UserRep userRep){
        this.userRep=userRep;

    }
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    InitializingBean init(){

    return () ->{

        if(roleRep.findAll().isEmpty())
        {
            Role roleUser = roleRep.save(new Role(Role.Types.ROLE_USER));
            Role roleAdmin = roleRep.save(new Role(Role.Types.ROLE_ADMIN));
            Role roleVip = roleRep.save(new Role(Role.Types.ROLE_VIP));

            User user = new User("user1234",true);
            user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
            user.setPassword(passwordEncoder.encode("user1234"));

            User admin = new User("admin1234",true);
            admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
            admin.setPassword(passwordEncoder.encode("admin1234"));

            User vip2 = new User("vip1234",true);
            vip2.setRoles(new HashSet<>(Arrays.asList(roleVip)));
            vip2.setPassword(passwordEncoder.encode("vip1234"));

            userRep.save(user);
            userRep.save(admin);
            userRep.save(vip2);

        }


        if(rodzajGryRep.findAll().isEmpty()){
            for(var rodzajGry: baza.rodzajGry) {
                rodzajGry.setId(null);

                rodzajGryRep.save(rodzajGry);
            }}




            if(rateRep.findAll().isEmpty()){
                for(var rate: baza.rateList){
                    rate.setId(null);
                    rateRep.save(rate);

                }



        }
        gameGenreRep.save(new GameGenre("Fantasy"));
        gameGenreRep.save(new GameGenre("Sci-Fi"));
        gameGenreRep.save(new GameGenre("Przygodowa"));
        gameGenreRep.save(new GameGenre("Strzelanka"));
        gameGenreRep.save(new GameGenre("FreeToPlay"));
        gameGenreRep.save(new GameGenre("MOBA"));
        gameGenreRep.save(new GameGenre("RPG"));
var dziedziny = gameGenreRep.findAll();
var rn=new Random();

    for (var game : baza.gameList) {
        game.setId(null);
        game.setGenres(new HashSet<>());
        for(int j=0,h=rn.nextInt(2)+1;j<h;j++)
        {
            var idx=rn.nextInt(7);
            game.getGenres().add(dziedziny.get(idx));
        }
        gameRepository.save(game);
    }
















    };

}
}
