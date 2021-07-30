package rasmus.olesen.message.broker.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rasmus.olesen.message.broker.core.rabbit.RabbitConfiguration;

@Log
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "rasmus.olesen.message.broker")
public class MessageBrokerConsumerApplication {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfiguration rabbitConfiguration;

    public static void main(String[] args) {
        log.info("Starting Application");
        SpringApplication.run(MessageBrokerConsumerApplication.class, args);
        log.info("Stopping Application");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {

        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(rabbitConfiguration.getQueueName());
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver messageReceiver) {
        final MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(messageReceiver);
        messageListenerAdapter.setMessageConverter(null); // Ensure that MessageReceiver gets the "raw" Message object.
        return messageListenerAdapter;
    }
}
