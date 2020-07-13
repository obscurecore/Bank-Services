package bank.service.finecardservice.service;


import bank.service.finecardservice.entity.FineCard;
import bank.service.finecardservice.client.common.UserData;
import reactor.core.publisher.Flux;

/**
 * The interface Fines service.
 *
 * @author Ruslan Potpaov
 */
public interface FinesService {
    Flux<FineCard> loadFines(UserData userData);
}
