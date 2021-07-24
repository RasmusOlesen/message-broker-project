package rasmus.olesen.message.broker.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MessageBrokerProducerApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MessageBrokerProducerApplication.class);

    public static void main(String[] args) {
        LOG.info("Starting Application");
        SpringApplication.run(MessageBrokerProducerApplication.class, args);
        LOG.info("Stopping Application");
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Starting Command Line Runner");
        final Scanner scanner = new Scanner(System.in);
        /*while (true) {
            Thread.sleep(1000);
        }*/
    }
}
