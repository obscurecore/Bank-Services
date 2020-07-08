package bank.service.validator;



import bank.service.dto.SignUpDto;
import bank.service.validator.contract.PasswordsEqualConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * Class is implementing ConstraintValidator<A extends Annotation,T> check if both strings is equal
 * @author Ruslan Potapov
 */
public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, Object> {

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
        var user = (SignUpDto) candidate;
        return user.getPassword().equals(user.getPasswordRepeat());
    }
}