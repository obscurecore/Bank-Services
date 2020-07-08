package bank.service.validator.contract;


import bank.service.validator.ReCaptchaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ReCaptchaValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ReCaptcha {
    public String message() default "{error.recaptcha.unsuccessful}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}