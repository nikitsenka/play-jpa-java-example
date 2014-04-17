package models.validators;

import play.data.validation.Constraints;
import play.libs.F;

import javax.validation.ConstraintValidator;

public class EanValidator extends Constraints.Validator<String>
        implements ConstraintValidator<EAN, String> {
    final static public String message = "error.invalid.ean";
    public EanValidator() {}
    @Override
    public void initialize(EAN constraintAnnotation) {}
    @Override
    public boolean isValid(String value) {
        String pattern = "^[0-9]{13}$";
        return value != null && value.matches(pattern);
    }
    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return new F.Tuple<String, Object[]>(message,new Object[]{});
    }
}