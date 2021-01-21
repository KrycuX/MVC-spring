package games.lab4.controllers.filters;


import games.lab4.models.GameGenre;
import games.lab4.models.Rate;
import games.lab4.models.RodzajGry;
import lombok.Getter;
import lombok.Setter;
import org.h2.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class GameSearchFilter {
    private String phrase="";
    @NumberFormat(pattern="#.00")
private Float minPrice;
    @NumberFormat(pattern="#.00")
private Float maxPrice;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
private LocalDate dateFrom;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
private LocalDate dateTo;

private List<RodzajGry> rodzajGryList= new ArrayList<>();
private List<Rate> rates=new ArrayList<>();
private List<GameGenre> gameGenres=new ArrayList<>();

public QUERY_MODE queryMode=QUERY_MODE.NAMED_METHOD;
public boolean empty(){

    return StringUtils.isNullOrEmpty(phrase) && minPrice == null && maxPrice == null;


}
public void clear(){
       this.phrase="";
       this.maxPrice=null;
       this.minPrice=null;

}
public String getPhraseLIKE(){
    return StringUtils.isNullOrEmpty(phrase)?"":"%"+phrase+"%";
}
public boolean isRodzajGryListEmpty(){return rodzajGryList.isEmpty();}
public boolean isGameGenresEmpty(){return gameGenres.isEmpty();}

public enum QUERY_MODE{

    NAMED_METHOD,
    NAMED_QUERY,
    QUERY,
    ENTITY_GRAPH,
    CRITERIA
}


}
