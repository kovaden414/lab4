package lab.lab4.repository;

import lab.lab4.entity.WorkType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WorkTypeRepository
        extends ReactiveCrudRepository<WorkType, Long> {
}
