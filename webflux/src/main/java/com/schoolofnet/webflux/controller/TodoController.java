package com.schoolofnet.webflux.controller;

import com.schoolofnet.webflux.model.Todo;
import com.schoolofnet.webflux.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 22/12/2021
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @ResponseBody
    public Flux<Todo> obterTodos() {
        return this.todoService.obterTodos();
    }

    @GetMapping("{id}")
    @ResponseBody
    public Mono<Todo> obterPorId(@PathVariable final Long id) {
        return todoService.obterPorId(id);
    }

    @PostMapping
    public Mono<Todo> salvar(@RequestBody final Todo todo) {
        return this.todoService.salvar(todo);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> excluir(@PathVariable final Long id) {
        return this.todoService.excluir(id);
    }
}
