package rasmus.olesen.message.broker.core.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Log
@RequiredArgsConstructor
@Component
public class RabbitWiring {

    private final RabbitConfiguration rabbitConfiguration;

    @Bean
    protected Queue queue() {
        return new Queue(rabbitConfiguration.getQueueName(), false);
    }

    @Bean
    protected TopicExchange exchange() {
        return new TopicExchange(rabbitConfiguration.getTopicExchangeName());
    }

    @Bean
    protected Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

}
