package bank.service.orderservice.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {

    String DIRECT = "direct";

    String BROADCAST = "broadcast";
    String data_copied = "data-copied";

    @Output(ProducerChannel.DIRECT)
    MessageChannel directMessage();

    @Output(ProducerChannel.BROADCAST)
    MessageChannel broadcastMessage();
    @Output(ProducerChannel.data_copied)
    MessageChannel keyMessage();
}
