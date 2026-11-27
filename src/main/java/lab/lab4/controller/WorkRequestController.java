package lab.lab4.controller;

import lab.lab4.entity.WorkRequest;
import lab.lab4.repository.WorkRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/work-requests")
@RequiredArgsConstructor
public class WorkRequestController {

    private final WorkRequestRepository repository;

    @GetMapping
    public Flux<WorkRequest> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<WorkRequest> getById(@PathVariable Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND)
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WorkRequest> create(
            @RequestBody WorkRequest request) {

        request.setId(null);
        return repository.save(request);
    }

    @PutMapping("/{id}")
    public Mono<WorkRequest> update(
            @PathVariable Long id,
            @RequestBody WorkRequest input) {

        return getById(id).flatMap(request -> {
            request.setTenantId(input.getTenantId());
            request.setWorkTypeId(input.getWorkTypeId());
            request.setScale(input.getScale());
            request.setPreferredTime(input.getPreferredTime());
            request.setStatus(input.getStatus());

            return repository.save(request);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return getById(id).flatMap(repository::delete);
    }
}
