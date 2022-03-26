package com.akvelon.task.tracker.model.enums;

import io.swagger.annotations.ApiModelProperty;

public enum ProjectStatus {
    @ApiModelProperty("Project is not started")
    NOT_STARTED,

    @ApiModelProperty("Project in process")
    ACTIVE,

    @ApiModelProperty("Project is completed")
    COMPLETED
}
