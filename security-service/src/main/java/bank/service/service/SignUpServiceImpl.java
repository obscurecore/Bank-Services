package bank.service.service;

import bank.service.dto.EmailDto;
import bank.service.dto.SignUpDto;
import bank.service.model.Role;
import bank.service.model.State;
import bank.service.model.User;
import bank.service.model.VerificationToken;
import bank.service.repository.UserRepository;
import bank.service.service.contract.SignUpService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.Random;
import java.util.UUID;

/**
 * Service for registration
 * @author Ruslan Potapov
 */
@Component
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    @Override
    public EmailDto signUp(SignUpDto form) {

        if (form.getUsername() == null) {
            form.setUsername("User" + new Random().nextInt(99999));
        }
        var uuid = UUID.randomUUID().toString();

        var verificationToken = new VerificationToken();
        verificationToken.setToken(uuid);

        var user = User.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .state(State.NOT_CONFIRMED)
                .roles(Collections.singleton(Role.USER))
                .verificationToken(verificationToken)
                .build();
        userRepository.save(user);

        return EmailDto.builder()
                .username(form.getUsername())
                .secret(uuid)
                .to(user.getEmail())
                .templateName(messageSource.getMessage("email.confirmation.title", null, LocaleContextHolder.getLocale()))
                .build();
    }
}
