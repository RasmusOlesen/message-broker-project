package rasmus.olesen.message.broker.consumer.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import rasmus.olesen.message.broker.consumer.jpa.entity.MessageEntity;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

}
