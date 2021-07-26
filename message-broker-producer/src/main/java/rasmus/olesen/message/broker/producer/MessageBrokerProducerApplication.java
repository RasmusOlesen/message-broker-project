package rasmus.olesen.message.broker.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.shell.standard.ShellMethod;
import rasmus.olesen.message.broker.core.MessageBrokerCoreApplication;

@Log
@RequiredArgsConstructor
public class MessageBrokerProducerApplication extends MessageBrokerCoreApplication {

    private final RabbitTemplate rabbitTemplate;

    @ShellMethod("Sends a message to RabbitMQ")
    private void sendMessage() {
        rabbitTemplate.convertAndSend(getTopicExchangeName(),"foo.bar.baz", "Hello from RabbitMQ!");
    }

}
