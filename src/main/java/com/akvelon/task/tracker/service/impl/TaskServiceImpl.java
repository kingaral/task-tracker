package com.akvelon.task.tracker.service.impl;

import com.akvelon.task.tracker.converter.TaskConverter;
import com.akvelon.task.tracker.converter.TaskDtoConverter;
import com.akvelon.task.tracker.exception.TaskTrackerException;
import com.akvelon.task.tracker.model.dto.TaskDto;
import com.akvelon.task.tracker.model.dto.TaskStatusChangeRequest;
import com.akvelon.task.tracker.model.entity.Task;
import com.akvelon.task.tracker.model.enums.TaskStatus;
import com.akvelon.task.tracker.repository.TaskRepository;
import com.akvelon.task.tracker.service.ProjectService;
import com.akvelon.task.tracker.service.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService {

    //services
    ProjectService projectService;

    // repository
    TaskRepository taskRepository;

    //converters
    TaskDtoConverter taskDtoConverter;
    TaskConverter taskConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void createTask(TaskDto taskDto) {
        Task task = taskDtoConverter.convert(taskDto);
        task.setProject(projectService.findProjectById(taskDto.getProjectId()));
        taskRepository.save(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskDto> getTasks(Specification<Task> spec, List<TaskStatus> statuses, Pageable pageable) {

        Specification<Task> projectSpec = Specification.where(spec);

        if (!CollectionUtils.isEmpty(statuses)) {
            Specification<Task> statusSpec = (root, qb, builder) ->
                    root.get("status").in(statuses.get(0));
            projectSpec.and(statusSpec);
        }

        return taskRepository.findAll(projectSpec, pageable)
                .map(taskConverter::convert);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void editTask(TaskDto taskDto, Long taskId) {
        Task task = taskDtoConverter.convert(taskDto);
        task.setId(taskId);
        taskRepository.save(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new TaskTrackerException
                                (HttpStatus.NOT_FOUND, "Cannot found task with id:" + taskId)
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public TaskDto getTaskDtoById(Long taskId) {
        return taskConverter.convert(findTaskById(taskId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void changeTaskStatusByIds(TaskStatusChangeRequest statusChangeRequest) {
        taskRepository.updateTaskStatusByIds(statusChangeRequest.getTaskIds(), statusChangeRequest.getStatus());
    }
}
