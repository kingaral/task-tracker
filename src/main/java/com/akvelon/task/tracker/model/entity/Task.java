package com.akvelon.task.tracker.model.entity;

import com.akvelon.task.tracker.model.enums.TaskStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    TaskStatus status;

    String description;

    Integer priority;

    @ManyToOne
    Project project;
}
