package bank.service.validator.contract;


import bank.service.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation
 *
 * @Constraint(…) —indicates what class is implementing the constraint for validation, more about it is covered in next part of the post,
 * @Retention(…) — in short, it indicates how long annotation will be making impact on our code (before or after compilation), in above case — RetentionPolicy.RUNTIME — it means that this annotation will be available after the runtime,
 * @Target(…) — indicates where this annotation can be applied, i.e. on a class, field, method.
 */
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueEmail {
    //declare all the parameters that can be add to annotation
    public String message() default "{error.email.unique}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}