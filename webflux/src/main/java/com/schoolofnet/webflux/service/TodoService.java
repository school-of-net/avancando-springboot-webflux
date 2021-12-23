package com.schoolofnet.webflux.service;

import com.schoolofnet.webflux.model.Todo;
import com.schoolofnet.webflux.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 22/12/2021
 */
@Service
public class TodoService {

    private final TodoRepository repository;
    private final TransactionTemplate transactionTemplate;

    @Qualifier("jdbcSchedule")
    private final Scheduler jdcScheduler;

    public TodoService(TodoRepository repository, TransactionTemplate transactionTemplate, Scheduler jdcScheduler) {
        this.repository = repository;
        this.transactionTemplate = transactionTemplate;
        this.jdcScheduler = jdcScheduler;
    }

    public Flux<Todo> obterTodos() {
        return Flux.defer(() -> Flux.fromIterable(this.repository.findAll())).subscribeOn(jdcScheduler);
    }

    public Mono<Todo> obterPorId(Long id) {
        return Mono.justOrEmpty(this.repository.findById(id));
    }

    public Mono<Todo> salvar(final Todo todo) {
        Mono op = Mono.fromCallable(() -> this.transactionTemplate.execute(action -> {
            final Todo newTodo = this.repository.save(todo);
            return newTodo;
        }));
        return op;
    }

    public Mono<ResponseEntity<Void>> excluir(Long id) {
        return Mono.fromCallable(() -> this.transactionTemplate.execute(action -> {
            this.repository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        })).subscribeOn(jdcScheduler);
    }
}
