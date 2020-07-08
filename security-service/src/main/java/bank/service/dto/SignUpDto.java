package bank.service.dto;

import bank.service.validator.contract.PasswordsEqualConstraint;
import bank.service.validator.contract.ReCaptcha;
import bank.service.validator.contract.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The dto to sign up
 *
 * @author Ruslan Potapov
 */
@Data
@Component
@PasswordsEqualConstraint // check if password and passwordRepeat is equal
public class SignUpDto {

    @ReCaptcha
    @JsonAlias("g-recaptcha-response")
    String captchaResponse;

    private String username;

    @NotBlank(message = "{error.field.not_blank}")
    @Email(message = "{error.email.incorrect}")
    @UniqueEmail // check if field is unique in BD
    private String email;

    @NotBlank(message = "{error.field.not_blank}")
    @Size(min = 5, message = "{error.password.size}")
    private String password;

    @NotBlank(message = "{error.field.not_blank}")
    private String passwordRepeat;
}