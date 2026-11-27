package lab.lab4.controller;

import lab.lab4.entity.WorkType;
import lab.lab4.repository.WorkTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/work-types")
@RequiredArgsConstructor
public class WorkTypeController {

    private final WorkTypeRepository repository;

    @GetMapping
    public Flux<WorkType> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<WorkType> getById(@PathVariable Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND)
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WorkType> create(@RequestBody WorkType type) {
        type.setId(null);
        return repository.save(type);
    }

    @PutMapping("/{id}")
    public Mono<WorkType> update(
            @PathVariable Long id,
            @RequestBody WorkType input) {

        return getById(id).flatMap(type -> {
            type.setName(input.getName());
            type.setDescription(input.getDescription());

            return repository.save(type);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return getById(id).flatMap(repository::delete);
    }
}
