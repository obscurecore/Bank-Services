package bank.service.orderservice.service;

import bank.service.orderservice.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBinding(Source.class)
@EnableScheduling
@AllArgsConstructor
class SenderService {

    private final Source source;

    @Autowired
    private final TicketGenerator ticketGenerator;

    @Scheduled(fixedRate = 5000)
    private void sendMessage() {
        Order editedOrder = ticketGenerator.createOrderForSend();
        System.err.println(editedOrder);
        source.output().send(MessageBuilder.withPayload(editedOrder).build());
    }

}
