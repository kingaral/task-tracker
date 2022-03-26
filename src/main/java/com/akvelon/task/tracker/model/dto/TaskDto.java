package com.akvelon.task.tracker.model.dto;

import com.akvelon.task.tracker.model.enums.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel("Model to manipulate with task")
public class TaskDto {

    @ApiModelProperty("Id of the task")
    Long id;

    @ApiModelProperty("Name of the task")
    @NotNull(message = "Name cannot be null")
    String name;

    @ApiModelProperty("Status of the task")
    @NotNull(message = "Status cannot be null")
    TaskStatus status;

    @ApiModelProperty("Full description of the task")
    @NotNull(message = "Description cannot be null")
    String description;

    @ApiModelProperty("Priority of the task")
    @NotNull(message = "Priority cannot be null")
    Integer priority;

    @ApiModelProperty("ProjectId which this task connected")
    @NotNull(message = "ProjectId cannot be null")
    Long projectId;
}
