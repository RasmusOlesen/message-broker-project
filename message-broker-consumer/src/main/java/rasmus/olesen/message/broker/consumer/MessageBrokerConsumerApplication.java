package rasmus.olesen.message.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MessageBrokerConsumerApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MessageBrokerConsumerApplication.class);

    public static void main(String[] args) {
        LOG.info("Starting Application");
        SpringApplication.run(MessageBrokerConsumerApplication.class, args);
        LOG.info("Stopping Application");
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Starting CommandLineRunner");
        System.out.print("Message Broker Consumer Application is now running.");
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            Thread.sleep(1000);
        }
    }
}
