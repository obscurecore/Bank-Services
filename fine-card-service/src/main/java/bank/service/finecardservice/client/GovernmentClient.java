package bank.service.finecardservice.client;


import bank.service.finecardservice.client.dto.FineDTO;
import bank.service.finecardservice.client.dto.FinesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type Government client.
 *
 * @author Ruslan Potapov
 */
@Service
public class GovernmentClient {

    private final WebClient client;

    public GovernmentClient(@Value("${fines.GovernmentServerURL}") String baseURL) {
        client = WebClient.create(baseURL);
    }

    public Mono<List<FineDTO>> getFines(String userId) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/fines").queryParam("userId", userId).build())
                .exchange()
                .flatMap(res -> res.bodyToMono(FinesResponse.class));
    }
}
