package games.lab4.config;

import games.lab4.baza;
import games.lab4.models.*;
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

@Autowired
    private DostawaRep dostawaRep;

@Autowired
private PlatnoscRep platnoscRep;

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
            user.setKoszyk(new Koszyk());
            user.setImie("Tomasz");
            user.setNazwisko("Krynicki");
            user.setMiasto("Turna");
            user.setUlica("");
            user.setNrDomu(13);

            User admin = new User("admin1234",true);
            admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setImie("Admin");
            admin.setNazwisko("Admin");
            admin.setMiasto("Wegrow");
            admin.setUlica("admintestul");
            admin.setNrDomu(21);

            User vip2 = new User("vip1234",true);
            vip2.setRoles(new HashSet<>(Arrays.asList(roleVip)));
            vip2.setPassword(passwordEncoder.encode("vip1234"));
            vip2.setImie("Tomasz");
            vip2.setNazwisko("Krynicki");
            vip2.setMiasto("Siedlce");
            vip2.setUlica("");
            vip2.setNrDomu(13);

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

            dostawaRep.save(new Dostawa("Odbior Osobisty",0.00f));
            dostawaRep.save(new Dostawa("Kurier",20.00f));
            dostawaRep.save(new Dostawa("Paczka Pocztowa",15.00f));
            dostawaRep.save(new Dostawa("Paczkomat",10.00f));

    platnoscRep.save(new Platnosc("Platność BLIK"));
    platnoscRep.save(new Platnosc("Gotówka"));
    platnoscRep.save(new Platnosc("Przelew Zwykly"));


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
