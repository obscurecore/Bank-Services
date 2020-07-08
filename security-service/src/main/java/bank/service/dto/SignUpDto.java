package bank.service.dto;

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
public class SignUpDto {

    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size
    private String password;

    @NotBlank
    private String passwordRepeat;
}