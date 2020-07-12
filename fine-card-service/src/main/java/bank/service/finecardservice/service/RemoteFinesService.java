package bank.service.finecardservice.service;


import bank.service.finecardservice.client.GovermentClient;
import bank.service.finecardservice.configuration.FinesProperties;
import bank.service.finecardservice.client.dto.CardType;
import bank.service.finecardservice.entity.FineCard;
import bank.service.finecardservice.client.dto.UserData;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RemoteFinesService implements FinesService {
    private final FinesProperties properties;
    private final GovermentClient GovermentClient;

    @Override
    public Flux<FineCard> loadFines(UserData userData) {
        return GovermentClient.getFines(userData.getUserId())
                .flatMapIterable(res-> res)
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
