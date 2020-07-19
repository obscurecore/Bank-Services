package bank.service.userservice.service;

import bank.service.userservice.client.common.Card;
import bank.service.userservice.client.common.UserData;
import reactor.core.publisher.Flux;

public interface CardsService {
    Flux<Card> loadCards(UserData userData);
}