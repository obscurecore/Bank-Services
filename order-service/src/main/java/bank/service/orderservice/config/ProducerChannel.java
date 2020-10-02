package bank.service.orderservice.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {

    String DIRECT = "direct";

    String BROADCAST = "broadcast";

    @Output(ProducerChannel.DIRECT)
    MessageChannel directMessage();

    @Output(ProducerChannel.BROADCAST)
    MessageChannel broadcastMessage();
}
