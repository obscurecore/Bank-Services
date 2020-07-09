package bank.service.finecardservice.service;


import bank.service.finecardservice.entity.FineCard;
import bank.service.finecardservice.client.dto.UserData;
import reactor.core.publisher.Flux;

public interface FinesService {
    Flux<FineCard> loadFines(UserData userData);
}
