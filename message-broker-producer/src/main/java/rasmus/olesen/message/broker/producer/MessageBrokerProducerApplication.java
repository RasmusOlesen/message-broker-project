package rasmus.olesen.message.broker.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import rasmus.olesen.message.broker.core.MessageSender;

@Log
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "rasmus.olesen.message.broker")
@EnableScheduling
public class MessageBrokerProducerApplication {

    private final MessageSender messageSender;

    public static void main(String[] args) {
        log.info("Starting Application");
        SpringApplication.run(MessageBrokerProducerApplication.class, args);
        log.info("Stopping Application");
    }


    @Scheduled(fixedDelay = 5000)
    private void workerTask() {
        log.finest("Running worker task");
        messageSender.sendMessage();
    }

}
