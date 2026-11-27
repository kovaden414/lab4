package lab.lab4.repository;

import lab.lab4.entity.Tenant;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TenantRepository
        extends ReactiveCrudRepository<Tenant, Long> {
}
