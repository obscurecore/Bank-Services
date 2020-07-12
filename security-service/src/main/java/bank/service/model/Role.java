package bank.service.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * The enum Role.
 *
 * @author Ruslan Potapov
 */
public enum Role implements GrantedAuthority, Serializable {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
