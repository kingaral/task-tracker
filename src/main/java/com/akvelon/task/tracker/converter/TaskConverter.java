package com.akvelon.task.tracker.converter;

import com.akvelon.task.tracker.model.dto.TaskDto;
import com.akvelon.task.tracker.model.entity.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter implements Converter<Task, TaskDto> {

    @Override
    public TaskDto convert(Task source) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(source.getId());
        taskDto.setName(source.getName());
        taskDto.setPriority(source.getPriority());
        taskDto.setStatus(source.getStatus());
        taskDto.setDescription(source.getDescription());
        taskDto.setProjectId(source.getProject().getId());
        return taskDto;
    }
}
