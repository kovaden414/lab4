package lab.lab4.controller;

import lab.lab4.entity.Tenant;
import lab.lab4.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantRepository repository;

    @GetMapping
    public Flux<Tenant> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Tenant> getById(@PathVariable Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND)
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tenant> create(@RequestBody Tenant tenant) {
        tenant.setId(null);
        return repository.save(tenant);
    }

    @PutMapping("/{id}")
    public Mono<Tenant> update(
            @PathVariable Long id,
            @RequestBody Tenant input) {

        return getById(id).flatMap(tenant -> {
            tenant.setFirstName(input.getFirstName());
            tenant.setLastName(input.getLastName());
            tenant.setAddress(input.getAddress());
            tenant.setPhone(input.getPhone());

            return repository.save(tenant);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return getById(id).flatMap(repository::delete);
    }
}
