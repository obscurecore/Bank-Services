package bank.service.bucketservice.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel {

    String DIRECT = "direct";

    String BROADCAST = "broadcast";

    @Input(ConsumerChannel.DIRECT)
    SubscribableChannel directMessage();

    @Input(ConsumerChannel.BROADCAST)
    SubscribableChannel broadcastMessage();
}