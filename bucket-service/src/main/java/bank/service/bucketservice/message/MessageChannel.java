package bank.service.bucketservice.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Subscriber applications will have their own queues subscribing to these Messages exchange..
 */
public interface MessageChannel {

    /**
     * Exchange in RabbitMQ named Messages.
     */
    String MESSAGES="messages";

    /**
     * Defining @Input channel similar to the how it is in the Sink interface provided by Spring Cloud Stream.
     * @return the subscribable channel
     */
    @Input
    SubscribableChannel messages();
}
