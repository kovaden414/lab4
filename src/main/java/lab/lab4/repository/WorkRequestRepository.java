package lab.lab4.repository;

import lab.lab4.entity.WorkRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WorkRequestRepository
        extends ReactiveCrudRepository<WorkRequest, Long> {
}
