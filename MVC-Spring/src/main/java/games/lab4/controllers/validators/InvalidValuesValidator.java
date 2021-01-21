package games.lab4.controllers.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InvalidValuesValidator implements ConstraintValidator<InvalidValues, String> {
    public InvalidValues constraint;


    @Override
    public void initialize(InvalidValues constraint){
        this.constraint=constraint;

    }



    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext constraintValidatorContext){

        if(fieldValue!=null)
        {
            if(constraint.ignoruj()==false)
            {
                for(var val:constraint.niedozwoloneWartosci())
                {
                    if(val.equals(fieldValue))
                        return false;
                }

            }else{
                for(var val :constraint.niedozwoloneWartosci())
                {
                    if(val.equals(fieldValue))
                    return false;

                }

            }
        }
return true;

    }

}
