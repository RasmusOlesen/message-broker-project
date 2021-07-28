package rasmus.olesen.message.broker.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log
@RequiredArgsConstructor
@Configuration
public class RabbitConfiguration {

    //TODO Config Properties
    @Getter
    private static String topicExchangeName = "spring-boot-exchange";
    @Getter
    private static String queueName = "spring-boot";

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
