package lab.lab4.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import lab.lab4.entity.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}

