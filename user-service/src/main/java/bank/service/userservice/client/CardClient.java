package bank.service.userservice.client;


import bank.service.userservice.client.common.Card;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

public interface CardClient<T extends Card> {
    Flux<T> getCards(String userId,
                            BigDecimal longitude,
                            BigDecimal latitude,
                            Long currentDate);
}
