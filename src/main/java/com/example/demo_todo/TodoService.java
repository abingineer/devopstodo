package com.example.demo_todo;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService{

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }
    
    public Todo save(TodoDTO todo) {

        Todo entity = new Todo();
        BeanUtils.copyProperties(todo, entity);

       return repository.save(entity);
    }
    
    public Todo findById(Long id) {
       return repository.findById(id).orElseThrow(
               ()-> new RuntimeException("Impossible de trouver le todo")
       );
    }
    
    public void update(Long id, TodoDTO todo) {

       var todoFound = repository.findById(id)
               .orElseThrow(()-> new RuntimeException("Impossible de trouver le todo"));

       todoFound.setTitle(todo.title());
       todoFound.setDescription(todo.description());

       repository.save(todoFound);
    }
    
    public List<Todo> findAll() {
        return repository.findAll();
    }
    
    public void delete(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists)
           throw  new RuntimeException("Impossible de trouver le todo");
        repository.deleteById(id);
    }
}
