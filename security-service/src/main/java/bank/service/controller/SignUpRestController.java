package bank.service.controller;

import bank.service.dto.SignUpDto;
import bank.service.service.contract.ConstraintService;
import bank.service.service.contract.SignUpService;
import bank.service.validator.contract.AccountEmail;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;

/**
 * Sign up rest controller.
 *
 * @author Ruslan Potapov
 */
@AllArgsConstructor
@RestController
@RequestMapping("/signUp")
@Validated
public class SignUpRestController {
    private final ConstraintService constraintService;
    private final SignUpService service;


    /**
     * Registry user with validate or throw exception
     *
     * @param signUpDto the dto
     * @param response  redirect
     */
    @SneakyThrows
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void signUp(@Valid @RequestBody SignUpDto signUpDto, HttpServletResponse response) {
        service.signUp(signUpDto);
        response.sendRedirect("signUp");
    }

    /**
     * Confirm registration. Validate with @AccountEmail
     *
     * @param link     the UUID code
     * @param response redirect
     */
    @SneakyThrows
    @GetMapping("/confirmation/{link}")
    @ResponseStatus(value = HttpStatus.OK)
    public void confirmRegistration(@AccountEmail @PathVariable("link") String link, HttpServletResponse response) {
        response.sendRedirect("/signUp");
    }

    /**
     * Handle validation exceptions
     *
     * @param ex the reports the result of constraint violations.
     * @return the JSON
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConstraintValidationExceptions(ConstraintViolationException ex) {
        return constraintService.getConstraintErrors(ex);
    }

    /**
     * Handle method validation exceptions
     *
     * @param ex the exception when validation on an argument annotated with @Valid fails.
     * @return the JSON
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodValidationExceptions(MethodArgumentNotValidException ex) {
        return constraintService.getMethodErrors(ex);
    }
}

