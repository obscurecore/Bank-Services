package bank.service.userservice.client;


import bank.service.userservice.client.dto.AdviceCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Service
public class AdviceCardClient implements CardClient<AdviceCard> {
    private final BaseCardClient cardClient;

    public AdviceCardClient(@Value("${hub.adviceURL}") String url) {
        this.cardClient = new BaseCardClient(url);
    }

    public Flux<AdviceCard> getCards(String userId,
                                     BigDecimal longitude,
                                     BigDecimal latitude,
                                     Long currentDate) {
        return cardClient.getCards(userId, longitude, latitude, currentDate, MediaType.APPLICATION_STREAM_JSON)
                .flatMapMany(res -> res.bodyToFlux(AdviceCard.class));
    }
}
