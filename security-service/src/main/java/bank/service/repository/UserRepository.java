package bank.service.repository;

import bank.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Ruslan Potapov
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByEmail(String email);

    Optional<User> findUserByVerificationToken_Token(String verificationToken_token);

    boolean existsUserByEmail(String email);

}
