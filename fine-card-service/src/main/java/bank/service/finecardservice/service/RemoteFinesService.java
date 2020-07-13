package bank.service.finecardservice.service;


import bank.service.finecardservice.client.GovernmentClient;
import bank.service.finecardservice.client.common.CardType;
import bank.service.finecardservice.client.common.UserData;
import bank.service.finecardservice.configuration.FinesProperties;
import bank.service.finecardservice.entity.FineCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * The type Remote fines service.
 *
 * @author Ruslan Potapov
 */
@Service
@RequiredArgsConstructor
public class RemoteFinesService implements FinesService {
    private final FinesProperties properties;
    private final GovernmentClient governmentClient;

    @Override
    public Flux<FineCard> loadFines(UserData userData) {
        return governmentClient.getFines(userData.getUserId())
                .flatMapIterable(res -> res)
                .map(fine ->
                        FineCard.builder()
                                .userId(userData.getUserId())
                                .dueDate(fine.getDueDate().getTime())
                                .amount(fine.getAmount())
                                .fineType(fine.getFineType())
                                .id(fine.getId())
                                .executionUrl(properties.getExecuteUrl())
                                .type(CardType.FINES)
                                .build()
                );
    }
}
