package com.example.demo_todo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TODO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
}
