package com.akvelon.task.tracker.controller;

import com.akvelon.task.tracker.model.dto.TaskDto;
import com.akvelon.task.tracker.model.dto.TaskStatusChangeRequest;
import com.akvelon.task.tracker.model.entity.Task;
import com.akvelon.task.tracker.model.enums.TaskStatus;
import com.akvelon.task.tracker.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/task")
@Api("Functions to manipulate with task")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @GetMapping
    @ApiOperation("Function to get tasks with filter and sort")
    public ResponseEntity<Page<TaskDto>> getTasks(
            @And({
                    @Spec(path = "name", spec = Like.class),
                    @Spec(path = "priority", params = {"priorityFrom", "priorityTo"}, spec = Between.class)
            }) Specification<Task> spec,
            @ApiParam("Statuses of the tasks for sort")
            @RequestParam(required = false) List<TaskStatus> statuses,
            @PageableDefault(sort = "priority", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasks(spec, statuses, pageable));
    }

    @GetMapping("/{taskId}")
    @ApiOperation("Function to get task by id")
    public ResponseEntity<TaskDto> getProjectsById(@ApiParam("ID of task")
                                                   @PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskDtoById(taskId));
    }

    @PostMapping
    @ApiOperation("Function to create task")
    public ResponseEntity<Void> createTask(@ApiParam(value = "Body of the request to crete task", required = true)
                                           @RequestBody @Valid TaskDto taskDto) {
        taskService.createTask(taskDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> editTask(@ApiParam("ID of task")
                                         @PathVariable Long taskId,
                                         @ApiParam(value = "Body of the request to edit task", required = true)
                                         @RequestBody @Valid TaskDto taskDto) {
        taskService.editTask(taskDto, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    @ApiOperation("Function to delete task by id")
    public ResponseEntity<Void> deleteTask(@ApiParam("ID of task")
                                           @PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/status")
    @ApiOperation("Function to change task status by list of task ids")
    public ResponseEntity<Void> changeStatus(
            @ApiParam(value = "Body of the request to change task status", required = true)
            @RequestBody @Valid TaskStatusChangeRequest statusChangeRequest) {
        taskService.changeTaskStatusByIds(statusChangeRequest);
        return ResponseEntity.ok().build();
    }
}
