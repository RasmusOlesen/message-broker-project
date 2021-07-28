package rasmus.olesen.message.broker.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;
import rasmus.olesen.message.broker.core.MessageSender;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Log
@RequiredArgsConstructor
@Component
public class MessageReceiver {

    private final MessageSender messageSender;

    static boolean isExpired(final Date timestamp) {
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - timestamp.getTime());
        if (minutes < 0) {
            log.warning("Invalid message timestamp from the future: " + timestamp);
            return true;
        }
        return minutes >= 1;
    }

    static boolean isEven(final Date timestamp) {
        return (TimeUnit.MILLISECONDS.toSeconds(timestamp.getTime()) % 2) == 0;
    }

    public void receiveMessage(byte[] message) {
        log.warning("byte[] message received: " + new String(message));
    }

    public void receiveMessage(Object message) {
        log.warning("Object message received: " + message.toString());
    }

    public void receiveMessage(Message message) {
        log.info("Message message received: " + new String(message.getBody()));

        Date timestamp = message.getMessageProperties().getTimestamp();
        if (timestamp == null) {
            log.warning("Message is missing timestamp");
            return;
        }

        if (isExpired(timestamp)) {
            log.info("Message has expired");
            return;
        }

        if (isEven(timestamp)) {
            log.info("Saving message with even timestamp: " + timestamp);
            //TODO Save in DB
            return;
        }

        log.info("Re-submitting message with uneven timestamp: " + timestamp);
        message.getMessageProperties().setTimestamp(null);
        messageSender.sendMessage(message);
    }


}
