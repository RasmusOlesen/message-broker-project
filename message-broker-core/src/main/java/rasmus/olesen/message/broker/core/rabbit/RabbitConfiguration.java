package rasmus.olesen.message.broker.core.rabbit;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RabbitConfiguration {

    @Value("message.broker.topic.exchange.name")
    private String topicExchangeName;
    @Value("message.broker.queue.name")
    private String queueName;

}
