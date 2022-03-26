package com.akvelon.task.tracker.converter;

import com.akvelon.task.tracker.model.dto.TaskDto;
import com.akvelon.task.tracker.model.entity.Task;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskDtoConverter implements Converter<TaskDto, Task> {

    @Override
    public Task convert(TaskDto source) {
        Task task = new Task();
        task.setName(source.getName());
        task.setPriority(source.getPriority());
        task.setDescription(source.getDescription());
        task.setStatus(source.getStatus());
        return task;
    }
}
