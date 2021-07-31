package rasmus.olesen.message.broker.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rasmus.olesen.message.broker.core.rabbit.RabbitConfiguration;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

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
        log.finest("Sending message: " + message.toString());
        final MessageProperties messageProperties = message.getMessageProperties();
        if (messageProperties.getTimestamp() == null) {
            messageProperties.setTimestamp(new Date());
        }
        if (messageProperties.getMessageId() == null) {
            messageProperties.setMessageId(Integer.toString(counter.getAndIncrement()));
        }
        rabbitTemplate.send(rabbitConfiguration.getTopicExchangeName(), rabbitConfiguration.getRoutingKey() + ".message", message);
    }

    @Async
    public void resubmitMessage(final Message message) {
        try {
            // This delay should really be handled by RabbitMQ (x-delay), but I had no luck getting the rabbitmq_delayed_message_exchange plugin to work.
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.log(Level.WARNING, "Failed to resubmit message", e);
        }
        Message newMessage = buildMessage(message.getBody());
        newMessage.getMessageProperties().setMessageId(message.getMessageProperties().getMessageId());
        sendMessage(newMessage);
    }

    Message buildMessage(final String body) {
        return buildMessage(body.getBytes(StandardCharsets.UTF_8));
    }

    Message buildMessage(final byte[] body) {
        return MessageBuilder.withBody(body).build();
    }
}
