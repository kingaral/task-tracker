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
public class Task extends AbstractEntity {

    /**
     * Name of this task
     */
    String name;

    /**
     * Status of the task
     */
    @Enumerated(EnumType.STRING)
    TaskStatus status;

    /**
     * Full description of this task
     */
    String description;

    /**
     * Priority of this task
     */
    Integer priority;

    /**
     * Relation with priority
     */
    @ManyToOne
    Project project;
}
