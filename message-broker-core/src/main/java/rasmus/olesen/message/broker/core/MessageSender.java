package rasmus.olesen.message.broker.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import rasmus.olesen.message.broker.core.rabbit.RabbitConfiguration;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Log
@RequiredArgsConstructor
@Component
public class MessageSender {

    private final static AtomicInteger counter = new AtomicInteger(0);
    private final ApplicationConfiguration applicationConfiguration;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfiguration rabbitConfiguration;

    public void sendMessage() {
        sendMessage("Hello from: " + applicationConfiguration.getApplicationName());
    }

    public void sendMessage(final String message) {
        sendMessage(buildMessage(message));
    }

    public void sendMessage(final Message message) {
        final MessageProperties messageProperties = message.getMessageProperties();
        if (messageProperties.getTimestamp() == null) {
            messageProperties.setTimestamp(new Date());
        }
        if(messageProperties.getMessageId() == null) {
            messageProperties.setMessageId(Integer.toString(counter.getAndIncrement()));
        }
        rabbitTemplate.send(rabbitConfiguration.getTopicExchangeName(), rabbitConfiguration.getRoutingKey() + ".message", message);
    }

    private Message buildMessage(final String message) {
        return MessageBuilder.withBody(message.getBytes(StandardCharsets.UTF_8)).build();
    }
}
