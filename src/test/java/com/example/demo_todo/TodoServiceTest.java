package com.example.demo_todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository repository;
    private TodoService underTest;

    @BeforeEach
    void setUp() {
         underTest = new TodoService(repository);
    }

    @Test
    void itShouldSave() {
        //Given
        var todo = new TodoDTO(
                "title todo",
                "description todo"
        );
        //When
         underTest.save(todo);
        //Then
        ArgumentCaptor<Todo> todoArgumentCaptor =
                ArgumentCaptor.forClass(Todo.class);

        verify(repository).save(todoArgumentCaptor.capture());

        var capturedTodo = todoArgumentCaptor.getValue();

        assertThat(capturedTodo.getTitle()).isEqualTo(todo.title());
        assertThat(capturedTodo.getDescription()).isEqualTo(todo.description());
    }

    @Test
    void itShouldFindById() {
        //Given
        Long id = 1L;
        var entity = new Todo(
                id,
                "title todo",
                "description todo"
        );

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        //When
        var expected = underTest.findById(id);
        //Then
        assertThat(expected).isEqualTo(entity);

    }

    @Test
    void itShouldNotFindById() {
        //Given
        Long id = 2L;
        var entity = new Todo(
                id,
                "title todo",
                "description todo"
        );

        when(repository.findById(id)).thenReturn(Optional.empty());
        //When
        //Then
         assertThatThrownBy(() -> underTest.findById(id))
                 .isInstanceOf(Exception.class)
                 .hasMessage("Impossible de trouver le todo");



    }

    @Test
    void itShouldUpdate() {
        //Given
        Long id = 2L;
        var entity = new Todo(
                id,
                "title todo",
                "description todo"
        );

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        TodoDTO updateRequest = new TodoDTO(
                "title todo 2",
                "Description todo 2"
        );
        //When
        underTest.update(id, updateRequest);
        //Then
        ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(repository).save(todoArgumentCaptor.capture());
        Todo capturedTodo = todoArgumentCaptor.getValue();

        assertThat(capturedTodo.getTitle()).isEqualTo(updateRequest.title());
        assertThat(capturedTodo.getDescription()).isEqualTo(updateRequest.description());

    }

    @Test
    void itShouldUpdateWhenIdDoesNotExist() {
        //Given
        Long id = 2L;
        var entity = new Todo(
                id,
                "title todo",
                "description todo"
        );

        when(repository.findById(id)).thenReturn(Optional.empty());
        TodoDTO updateRequest = new TodoDTO(
                "title todo 2",
                "Description todo 2"
        );
        //When
        //Then
        assertThatThrownBy(() ->  underTest.update(id, updateRequest))
                .isInstanceOf(Exception.class)
                .hasMessage("Impossible de trouver le todo");

        verify(repository, never()).save(any());


    }
    @Test
    void itShouldFindAll() {
        //Given
        //When
        underTest.findAll();
        //Then
        verify(repository).findAll();
    }

    @Test
    void itShouldDelete() {
        //Given
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);
        //When
        underTest.delete(id);
        //Then
        verify(repository).deleteById(any());
    }

    @Test
    void itShouldNotDeleteWhenIdDoesNotExist() {
        //Given
        Long id = 3L;
        when(repository.existsById(id)).thenReturn(false);
        //When
        //Then
        assertThatThrownBy(() -> underTest.delete(id))
                .isInstanceOf(Exception.class)
                        .hasMessage("Impossible de trouver le todo");

        verify(repository, never()).deleteById(any());
    }
}