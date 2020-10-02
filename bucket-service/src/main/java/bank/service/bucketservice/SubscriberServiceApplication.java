package bank.service.bucketservice;

import bank.service.bucketservice.config.ConsumerChannel;
import bank.service.bucketservice.message.MessageChannel;
import bank.service.bucketservice.message.MyMessage;
import bank.service.bucketservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * The type Subscriber service application.
 */
@SpringBootApplication
@EnableBinding({Sink.class, ConsumerChannel.class})
@Slf4j
public class SubscriberServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(SubscriberServiceApplication.class, args);
	}

	/**
	 * Attaching a listener method to this channel.
	 * @param message the message
	 */
/*
	@StreamListener(target = Sink.INPUT)
	public void handleMessage(Message message) throws Exception {
		if(message.getMessage().contains("fuck")) {
			throw new Exception("Error spam!!!");
		}
		System.out.println("Spam message: " + message);
	}*/
	@StreamListener(MessageChannel.MESSAGES)
	public void handleMessage(Message message){
		System.out.println("Subscriber Received Message is: " + message);
	}

	@StreamListener(ConsumerChannel.DIRECT)
	public void handleDirect(MyMessage message) {
		log.info("##### direct message : {}", message.getMessage());
	}

	@StreamListener(ConsumerChannel.BROADCAST)
	public void handleBroadcast(MyMessage message) {
		log.info("##### broadcast message : {}", message.getMessage());
	}
}
