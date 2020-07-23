package bank.service.service;

import bank.service.dto.EmailDto;
import bank.service.dto.SignUpDto;
import bank.service.model.Role;
import bank.service.model.State;
import bank.service.model.User;
import bank.service.model.VerificationToken;
import bank.service.repository.UserRepository;
import bank.service.service.contract.SignUpService;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpServiceImplTest {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    SignUpServiceImpl signUpService = mock(SignUpServiceImpl.class);

    @Test
    public void signUpReturnTest() {
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setUsername("test");
        EmailDto emailDto = new EmailDto();
        doReturn(emailDto).when(signUpService).signUp(signUpDto);
    }
}