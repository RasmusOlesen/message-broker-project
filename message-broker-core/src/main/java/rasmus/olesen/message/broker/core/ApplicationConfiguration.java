package rasmus.olesen.message.broker.core;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationConfiguration {
    @Value("${spring.application.name}")
    private String applicationName;
}
