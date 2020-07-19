package bank.service.userservice.client.dto;


import bank.service.userservice.client.common.Card;
import bank.service.userservice.client.common.CardType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor

public class RegularCard extends Card {
    private String targetAccount;
    private Long dueDate;
    @Builder
    public RegularCard(String id, String userId, CardType type, BigDecimal amount,
                       String executionUrl, String targetAccount, Long dueDate) {
        super(id, userId, type, amount, executionUrl);
        this.targetAccount = targetAccount;
        this.dueDate = dueDate;
    }
}
