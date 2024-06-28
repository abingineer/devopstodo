package com.example.demo_todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public Todo save(@RequestBody TodoDTO todo) {
        return todoService.save(todo);
    }

    @PutMapping("{id}")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody TodoDTO todoDTO) {
        todoService.update(id, todoDTO);
    }

    @GetMapping
    public List<Todo> findAll(){
      return todoService.findAll();
    }

    @GetMapping(value = "{id}")
    public Todo findById(@PathVariable(name = "id") Long id) {
        return todoService.findById(id);
    }
}
