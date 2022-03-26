package com.akvelon.task.tracker.model.entity;

import com.akvelon.task.tracker.model.enums.ProjectStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "project")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;

    String name;
    Date startDate;
    Date endDate;

    @Enumerated(EnumType.STRING)
    ProjectStatus status;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    Set<Task> tasks;

    Integer priority;
}
