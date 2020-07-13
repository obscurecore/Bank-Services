package bank.service.finecardservice.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The type Fines properties.
 * @author Ruslan Potapov
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "fines")
public class FinesProperties {
    private String executeUrl;
}
