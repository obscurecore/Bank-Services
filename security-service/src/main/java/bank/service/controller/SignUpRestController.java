package bank.service.controller;

import bank.service.dto.SignUpDto;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    /**
     * Registry user with validate or throw exception
     *
     * @param signUpDto the dto
     * @param response  redirect
     * @author Ruslan Potpaov
     */
    @SneakyThrows
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void signUp(@Valid @RequestBody SignUpDto signUpDto, HttpServletResponse response) {
        response.sendRedirect("signUp");
    }
}

