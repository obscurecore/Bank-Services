package bank.service.validator;

import bank.service.repository.UserRepository;
import bank.service.validator.contract.UniqueEmail;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class is implementing ConstraintValidator<A extends Annotation,T> check if the email is unique in DB
 *
 * @author Ruslan Potapov
 */
@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userRepository.existsUserByEmail(value);

    }
}