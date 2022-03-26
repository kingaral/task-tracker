package com.akvelon.task.tracker.service;

import com.akvelon.task.tracker.model.dto.ProjectDto;
import com.akvelon.task.tracker.model.dto.TaskDto;
import com.akvelon.task.tracker.model.dto.TaskStatusChangeRequest;
import com.akvelon.task.tracker.model.entity.Project;
import com.akvelon.task.tracker.model.entity.Task;
import com.akvelon.task.tracker.model.enums.ProjectStatus;
import com.akvelon.task.tracker.model.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Functions to control project
 */
public interface TaskService {

    /**
     * Method to create task
     * @param taskDto {@link TaskDto}
     */
    void createTask(TaskDto taskDto);

    /**
     * Method to get list of projects
     * @param spec Specification to filter data
     * @param statuses List of {@link TaskStatus}
     * @param pageable pageable for sorting and for pageable
     * @return list of page  {@link  TaskDto}
     */
    Page<TaskDto> getTasks(Specification<Task> spec, List<TaskStatus> statuses, Pageable pageable);

    /**
     * Method to edit list of projects
     * @param taskDto {@link TaskDto}
     * @param taskId id of the task {@link Task#getId}
     */
    void editTask(TaskDto taskDto, Long taskId);

    /**
     * Method for deleting task
     * @param taskId  id of the task {@link Task#getId}
     */
    void deleteTask(Long taskId);

    /**
     * Method for finding task by id
     * @param taskId id of the task {@link Task#getId}
     * @return entity {@link  Task}
     */
    Task findTaskById(Long taskId);

    /**
     *  Method for finding taskDto by id
     * @param taskId id of the task {@link Task#getId}
     * @return Dto {@link  TaskDto}
     */
    TaskDto getTaskDtoById(Long taskId);

    /**
     * Method for changing task status by list of ids
     * @param statusChangeRequest {@link TaskStatusChangeRequest}
     */
    void changeTaskStatusByIds (TaskStatusChangeRequest statusChangeRequest);
}
