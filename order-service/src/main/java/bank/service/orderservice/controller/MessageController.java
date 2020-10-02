package bank.service.orderservice.controller;

import bank.service.orderservice.config.ProducerChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Foreign event-driven sources
 * Deliver data originating from outside to Stream via REST calls
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    // Bridging non-stream application with spring-cloud-stream.
    private final StreamBridge streamBridge;

    /**
     * @param message  String
     * @return StreamBridge to pass data originating from outside through REST calls to Stream. The default message type is application/json
     */
    @GetMapping(value = "/direct/{message}")
    public Mono<Void> directMessage(@PathVariable String message) {

        return Mono.just(message)
                .doOnNext(s -> streamBridge.send(ProducerChannel.DIRECT, MyMessage.builder().message(message).build()))
                .then();
    }

    @GetMapping(value = "/broadcast/{message}")
    public Mono<Void> broadcastMessage(@PathVariable String message) {

        return Mono.just(message)
                .doOnNext(s -> streamBridge.send(ProducerChannel.BROADCAST, MyMessage.builder().message(message).build()))
                .then();
    }
}