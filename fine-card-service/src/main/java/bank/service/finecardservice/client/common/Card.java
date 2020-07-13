package bank.service.finecardservice.client.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * The type Card.
 *
 * @author Ruslan Potapov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String id;
    private String userId;
    private CardType type;
    private BigDecimal amount;
    private String executionUrl;


}