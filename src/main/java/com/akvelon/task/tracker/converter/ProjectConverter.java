package com.akvelon.task.tracker.converter;

import com.akvelon.task.tracker.model.dto.ProjectDto;
import com.akvelon.task.tracker.model.entity.Project;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectConverter implements Converter<Project, ProjectDto> {

    TaskConverter taskConverter;

    @Override
    public ProjectDto convert(Project source) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(source.getId());
        projectDto.setName(source.getName());
        projectDto.setStartDate(source.getStartDate());
        projectDto.setEndDate(source.getEndDate());
        projectDto.setStatus(source.getStatus());
        projectDto.setPriority(source.getPriority());
        projectDto.setTasks(
                source.getTasks()
                        .stream()
                        .map(taskConverter::convert)
                        .collect(Collectors.toList())
        );
        return projectDto;
    }
}
