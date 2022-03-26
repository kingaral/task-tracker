package com.akvelon.task.tracker.model.dto;

import com.akvelon.task.tracker.model.enums.ProjectStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel("Model to manipulate with project")
public class ProjectDto {

    @ApiModelProperty("Id of the project")
    Long id;

    @ApiModelProperty("Name of the project")
    @NotNull(message = "Name should not be null")
    String name;

    @ApiModelProperty("Start date of the project")
    @NotNull(message = "StartDate should not be null")
    Date startDate;

    @ApiModelProperty("End date of the project")
    @NotNull(message = "EndDate should not be null")
    Date endDate;

    @ApiModelProperty("Status of the project")
    @NotNull(message = "Status should not be null")
    ProjectStatus status;

    @ApiModelProperty("Tasks of this project")
    List<TaskDto> tasks;

    @ApiModelProperty("Priority of this project")
    @NotNull(message = "Priority should not be null")
    Integer priority;
}
