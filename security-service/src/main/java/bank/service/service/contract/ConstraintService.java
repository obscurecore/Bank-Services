package bank.service.service.contract;

import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.Map;

/**
 * The interface Constraint service.
 * @author Ruslan Potapov
 */
public interface ConstraintService {
    Map<String, String> getMethodErrors(MethodArgumentNotValidException ex);

    Map<String, String> getConstraintErrors(ConstraintViolationException constraintViolationException);

}
