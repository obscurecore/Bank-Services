package bank.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Default controller.
 *
 * @author Ruslan Potpaov
 */
@Controller
public class DefaultController {
    @GetMapping("/")
    public String getRoot() {
        return "login";
    }

    @GetMapping("/login")
    public String getSignInPage() {
        return "login";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

}
