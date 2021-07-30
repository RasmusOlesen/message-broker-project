package rasmus.olesen.message.broker.consumer.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import rasmus.olesen.message.broker.consumer.jpa.entity.MessageEntity;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends CrudRepository<MessageEntity,Long> {

    //List<MessageEntity> findByMessage(final String message);
    //List<MessageEntity> findByTimestamp(final Date timestamp);
    //MessageEntity findById(final long id);

}
