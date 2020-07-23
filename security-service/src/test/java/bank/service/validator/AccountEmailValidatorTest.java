package bank.service.validator;

import bank.service.model.User;
import bank.service.repository.UserRepository;
import bank.service.validator.contract.AccountEmail;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountEmailValidatorTest {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    @Mock
    AccountEmailValidator accountEmailValidator;
    @Mock
    ConstraintValidatorContext constraintValidatorContext;
    @Test
    void isValidInvokeFindByTokenError() {
        String verToken = UUID.randomUUID().toString();
        accountEmailValidator.initialize(any(AccountEmail.class));
        boolean actual = accountEmailValidator.isValid(verToken,constraintValidatorContext);
        when(userRepository.findUserByVerificationToken_Token(anyString())).thenReturn(null);
        assertFalse(actual);
        Mockito.verify(userRepository,Mockito
                .times(0))
                .findUserByVerificationToken_Token(verToken);
    }
}