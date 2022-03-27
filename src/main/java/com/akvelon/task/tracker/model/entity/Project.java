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
public class Project extends AbstractEntity{

    /**
     * Name of the project
     */
    String name;


    /**
     * Start date  of the project
     */
    Date startDate;

    /**
     * End date  of the project
     */
    Date endDate;


    /**
     * Status date  of the project: NotStarted, Active, Completed
     */
    @Enumerated(EnumType.STRING)
    ProjectStatus status;

    /**
     * OneToMany relation to see tasks witch connected to this project
     */

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    Set<Task> tasks;

    /**
     * Priority of this project
     */
    Integer priority;
}
