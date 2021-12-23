package com.schoolofnet.webflux.controller;

import com.schoolofnet.webflux.model.Todo;
import com.schoolofnet.webflux.service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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

    @PostMapping
    public Mono<Todo> salvar(@RequestBody final Todo todo) {
        return this.todoService.salvar(todo);
    }
}
