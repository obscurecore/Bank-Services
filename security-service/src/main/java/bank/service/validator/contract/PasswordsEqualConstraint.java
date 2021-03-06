package bank.service.validator.contract;


import bank.service.validator.PasswordsEqualConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The interface Passwords equal constraint.
 *
 * @author Ruslan Potapov
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordsEqualConstraintValidator.class)

public @interface PasswordsEqualConstraint {
    String message() default "{error.password.unequal}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}