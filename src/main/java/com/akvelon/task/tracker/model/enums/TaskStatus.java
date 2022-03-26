package com.akvelon.task.tracker.model.enums;

import io.swagger.annotations.ApiModelProperty;

public enum TaskStatus {

    @ApiModelProperty("Task which need to do")
    TO_DO,

    @ApiModelProperty("Task which in process ")
    IN_PROGRESS,

    @ApiModelProperty("Task which is done")
    DONE
}
