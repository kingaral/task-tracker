package com.akvelon.task.tracker.model.dto;

import com.akvelon.task.tracker.model.enums.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel("Model to change task status")
public class TaskStatusChangeRequest {

    @ApiModelProperty("List of task ids")
    @NotNull(message = "TaskIds cannot be null")
    List<Long> taskIds;

    @ApiModelProperty("Status of the task")
    @NotNull(message = "Status cannot be null")
    TaskStatus status;
}
