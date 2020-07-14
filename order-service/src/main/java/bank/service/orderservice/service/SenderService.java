package bank.service.orderservice.service;


import bank.service.orderservice.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBinding
@EnableScheduling
@AllArgsConstructor
public class SenderService {

    private final Source source;

    @Scheduled(fixedRate = 5000)
    private void sendMessage(){
        Message message = new Message("Send message from publisher/Sheduler");
        System.err.println(message.getMessage());
        source.output().send(MessageBuilder.withPayload(message).build());
    }


}

