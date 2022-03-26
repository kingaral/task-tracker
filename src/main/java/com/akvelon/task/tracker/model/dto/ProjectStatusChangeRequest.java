package com.akvelon.task.tracker.model.dto;

import com.akvelon.task.tracker.model.enums.ProjectStatus;
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
@ApiModel("Model to change project status")
public class ProjectStatusChangeRequest {

    @ApiModelProperty("List of project ids")
    @NotNull(message = "ProjectIds cannot be null")
    List<Long> projectIds;

    @ApiModelProperty("Status of the project")
    @NotNull(message = "Status cannot be null")
    ProjectStatus status;
}
