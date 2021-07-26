package rasmus.olesen.message.broker.core;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log
@SpringBootApplication
public class MessageBrokerCoreApplication {

    @Getter
    static final String topicExchangeName = "spring-boot-exchange";
    @Getter
    static final String queueName = "spring-boot";

    public static void main(String[] args) {
        log.info("Starting Application");
        SpringApplication.run(MessageBrokerCoreApplication.class, args);
        log.info("Stopping Application");
    }

    @Bean
    protected Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    protected TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    protected Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }
}
