package bank.service.userservice.service;


import bank.service.userservice.client.CardClient;
import bank.service.userservice.client.common.Card;
import bank.service.userservice.client.common.UserData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemoteCardsHubService implements CardsService {
    private final List<CardClient> cardClients;

    @Override
    public Flux<Card> loadCards(UserData userData) {
        final List<Flux<Card>> fluxList = cardClients.stream()
                .map(client -> getCards(userData, client))
                .collect(Collectors.toList());
        return Flux.merge(fluxList);
    }

    @SuppressWarnings("unchecked")
    private Flux<Card> getCards(UserData userData, CardClient client) {
        return client.getCards(userData.getUserId(),
                userData.getGeoPosition().getLongitude(), userData.getGeoPosition().getLatitude(),
                userData.getCurrentDate());
    }
}