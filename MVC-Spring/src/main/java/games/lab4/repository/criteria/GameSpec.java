package games.lab4.repository.criteria;

import games.lab4.models.Game;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class GameSpec {

    public static Specification<Game> findByPhrase(final String phrase){
        return (root,query,cb)->{
            if(StringUtils.isEmpty(phrase)==false){
                String phraseLike = "%"+phrase.toUpperCase()+"%";
                return cb.or(
                        cb.like(cb.upper(root.get("title")),phraseLike),
                        cb.like(cb.upper(root.get("author")),phraseLike),
                        cb.like(cb.upper(root.get("rodzajGry").get("name")),phraseLike)
                );
            }return null;
        };
    }

    public static Specification<Game> findByPriceRange(Float minPrice, Float maxPrice){
        return (root,query,cb)->{
          if(minPrice != null && maxPrice !=null)  {
return cb.between(root.get("price"),minPrice,maxPrice);
          }else if(minPrice!=null)
          {
             return cb.greaterThanOrEqualTo(root.get("price"),minPrice);
          }else if(maxPrice!=null){
              return cb.lessThanOrEqualTo(root.get("price"),maxPrice);
          }
          return null;
        };
    }
}
