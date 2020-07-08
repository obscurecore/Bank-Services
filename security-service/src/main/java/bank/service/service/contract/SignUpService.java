package bank.service.service.contract;


import bank.service.dto.EmailDto;
import bank.service.dto.SignUpDto;

/**
 * The interface Sign up service.
 * @author Ruslan Potapov
 */
public interface SignUpService {
    EmailDto signUp(SignUpDto s);
}