package rasmus.olesen.message.broker.consumer;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class MessageReceiver {

    public void receiveMessage(final String message) {
        log.info(message);
    }
}
