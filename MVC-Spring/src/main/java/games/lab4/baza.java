package games.lab4;

import games.lab4.models.Game;
import games.lab4.models.Rate;
import games.lab4.models.RodzajGry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class baza {
    public static List<Game> gameList;
    public static List<RodzajGry> rodzajGry;
    public static List<Rate> rateList;


    static{



        LocalDate data1 =LocalDate.of(1999,5,25);
        LocalDate data2 =LocalDate.of(1995,3,10);
        LocalDate data3 =LocalDate.of(2005,12,31);
        LocalDate data4 =LocalDate.of(2004,9,27);
        rateList=new ArrayList<>();
        rodzajGry=new ArrayList<>();
        gameList=new ArrayList<>();

        int rid=0;

        rateList.add(new Rate(rid,"Świetna!"));
        rateList.add(new Rate(++rid,"Dobra!"));
        rateList.add(new Rate(++rid,"Przeciętna!"));
        rateList.add(new Rate(++rid,"Słaba!"));

        int rodzid=0;

        rodzajGry.add(new RodzajGry(rodzid,"Pudełkowa"));
        rodzajGry.add(new RodzajGry(++rodzid,"Cyfrowa"));
        rodzajGry.add(new RodzajGry(++rodzid,"Pre-order"));
        rodzajGry.add(new RodzajGry(++rodzid,"Cyfrowa i Pudełkowa"));

        var game=new Game(1l,"Cyberpunk","CDP",data1,(float)229.99,true);
        game.setRate(rateList.get(0));
        game.setRodzajGry(rodzajGry.get(0));
        gameList.add(game);

         game=new Game(2l,"Assasin's Creed: Valhala","Ubisoft",data2,(float)269.99,true);
        game.setRate(rateList.get(2));
        game.setRodzajGry(rodzajGry.get(2));
        gameList.add(game);

        game=new Game(3l,"Wiedzmin 3","CDP",data3,(float)75.99,true);
        game.setRate(rateList.get(1));
        game.setRodzajGry(rodzajGry.get(1));
        gameList.add(game);

        game=new Game(4l,"World of Warcraft: Shadowlands","Blizzard",data4,(float)119.99,true);
        game.setRate(rateList.get(3));
        game.setRodzajGry(rodzajGry.get(3));
        gameList.add(game);

        game=new Game(5l,"Hitman","Nieznam",data4,(float)49.99,false);
        game.setRate(rateList.get(3));
        game.setRodzajGry(rodzajGry.get(3));
        gameList.add(game);

        game=new Game(6l,"ARK:SurvivalEevolved","Studio Wildcard",data4,(float)99.99,false);
        game.setRate(rateList.get(3));
        game.setRodzajGry(rodzajGry.get(3));
        gameList.add(game);

        game=new Game(7l,"NFS:***","Nieznany",data4,(float)99.99,false);
        game.setRate(rateList.get(3));
        game.setRodzajGry(rodzajGry.get(3));
        gameList.add(game);

        game=new Game(8l,"Among Us","InnerSloth",data4,(float)17.99,true);
        game.setRate(rateList.get(3));
        game.setRodzajGry(rodzajGry.get(3));
        gameList.add(game);

        game=new Game(9l,"CS:GO","Gejben",data4,(float)19.99,false);
        game.setRate(rateList.get(3));
        game.setRodzajGry(rodzajGry.get(3));
        gameList.add(game);





    }
}
