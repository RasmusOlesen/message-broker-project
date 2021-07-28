package rasmus.olesen.message.broker.core.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import rasmus.olesen.message.broker.core.MessageSender;

@Log
@RequiredArgsConstructor
@ShellComponent
public class MessageBrokerShell {

    private final MessageSender messageSender;

    @ShellMethod("Sends a message to RabbitMQ") //TODO parameters
    public void sendMessage() {
        messageSender.sendMessage();
    }
}
