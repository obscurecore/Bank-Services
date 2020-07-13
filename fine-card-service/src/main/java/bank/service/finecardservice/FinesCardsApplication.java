package bank.service.finecardservice;

import bank.service.finecardservice.configuration.FinesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * The type Fines cards application.
 *
 * @author Ruslan Potapov
 */
@SpringBootApplication
@EnableConfigurationProperties({FinesProperties.class})
public class FinesCardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinesCardsApplication.class, args);
    }
}
