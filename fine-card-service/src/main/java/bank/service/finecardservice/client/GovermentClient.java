package bank.service.finecardservice.client;


import bank.service.finecardservice.client.dto.FineDTO;
import bank.service.finecardservice.client.dto.FinesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GovermentClient {

    private final WebClient client;

    public GovermentClient(@Value("${fines.AIServerURL}") String baseURL) {
        client = WebClient.create(baseURL);
    }

    public Mono<List<FineDTO>> getFines(String userId) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/fines").queryParam("userId", userId).build())
                .exchange()
                .flatMap(res -> res.bodyToMono(FinesResponse.class));
    }
}
