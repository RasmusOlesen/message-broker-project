package rasmus.olesen.message.broker.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

// Prevent shell from blocking test execution
// https://github.com/spring-projects/spring-shell/issues/204
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class MessageBrokerCoreApplicationTests {

    @Test
    void contextLoads() {
    }

}
