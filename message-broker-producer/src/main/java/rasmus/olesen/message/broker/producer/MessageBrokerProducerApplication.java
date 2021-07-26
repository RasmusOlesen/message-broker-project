package rasmus.olesen.message.broker.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageBrokerProducerApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MessageBrokerProducerApplication.class);

    public static void main(String[] args) {
        LOG.info("Starting Application");
        SpringApplication.run(MessageBrokerProducerApplication.class, args);
        LOG.info("Stopping Application");
    }
}
