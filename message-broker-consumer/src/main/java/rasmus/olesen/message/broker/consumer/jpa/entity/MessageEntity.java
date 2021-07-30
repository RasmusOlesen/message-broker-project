package rasmus.olesen.message.broker.consumer.jpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private byte[] body;
    private Date timestamp;
    private String messageId;

    public MessageEntity(final byte[] body, final Date timestamp, final String messageId) {
        this.body = body;
        this.timestamp = timestamp;
        this.messageId = messageId;
    }

    public static MessageEntity getMessageEntity(@NotNull final Message message) {
        return new MessageEntity(message.getBody(), message.getMessageProperties().getTimestamp(), message.getMessageProperties().getMessageId());
    }

}
