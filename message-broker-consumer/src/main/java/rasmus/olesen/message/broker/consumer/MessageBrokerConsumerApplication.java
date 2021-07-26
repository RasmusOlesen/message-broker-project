package rasmus.olesen.message.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageBrokerConsumerApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MessageBrokerConsumerApplication.class);

    public static void main(String[] args) {
        LOG.info("Starting Application");
        SpringApplication.run(MessageBrokerConsumerApplication.class, args);
        LOG.info("Stopping Application");
    }
}
