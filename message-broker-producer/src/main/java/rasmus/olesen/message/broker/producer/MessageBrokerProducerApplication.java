package rasmus.olesen.message.broker.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import rasmus.olesen.message.broker.core.RabbitConfiguration;

@ShellComponent
@Log
@RequiredArgsConstructor
@SpringBootApplication
public class MessageBrokerProducerApplication {

    private final RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        log.info("Starting Application");
        SpringApplication.run(MessageBrokerProducerApplication.class, args);
        log.info("Stopping Application");
    }


    @ShellMethod("Sends a message to RabbitMQ") //TODO Debug parameters
    private void sendMessage() {
        rabbitTemplate.convertAndSend(RabbitConfiguration.getTopicExchangeName(), "foo.bar.baz", "Hello from RabbitMQ!");
    }


}
