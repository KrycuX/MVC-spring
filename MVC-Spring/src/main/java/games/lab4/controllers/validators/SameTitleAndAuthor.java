package games.lab4.controllers.validators;

import games.lab4.models.Game;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SameTitleAndAuthor implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
return aClass.isAssignableFrom(Game.class);
    }

    @Override
    public void validate (Object o, Errors errors)
    {
        var game=(Game)o;
        if(game!=null)
        {
            if(game.getAuthor().equalsIgnoreCase(game.getTitle())){
                errors.rejectValue("author","theSameValues.gry.author");
            }
        }
    }
}