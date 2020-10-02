package bank.service.orderservice.controller;

import bank.service.orderservice.config.ProducerChannel;
import bank.service.orderservice.config.TopicConfig;
import bank.service.orderservice.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static javax.swing.UIManager.put;

@RestController
@EnableBinding({Source.class, ProducerChannel.class})
public class PublisherController {

    @Autowired
    private Source source;
    private ProducerChannel producerChannel;

    @GetMapping(value = "/send")
    public void sendMessage() {
        Message message = new Message("Send message from publisher");
        source.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping(value = "/send/spam")
    public Message sendSpamMessage() {
        Message message = new Message("I'm sorry, but fuck you");
        producerChannel.keyMessage().send(MessageBuilder.withPayload(message).setHeader("type", "key-stuff").build());

        return message;
/*
        Message message = new Message("I'm sorry, but fuck you");
        source.output().send(MessageBuilder.withPayload(message).build());
        return message;*/
    }

}
