package com.akvelon.task.tracker.converter;

import com.akvelon.task.tracker.model.dto.ProjectDto;
import com.akvelon.task.tracker.model.entity.Project;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectDtoConverter implements Converter<ProjectDto, Project> {

    TaskDtoConverter taskDtoConverter;

    @Override
    public Project convert(ProjectDto source) {

        Project project = new Project();
        project.setStartDate(source.getStartDate());
        project.setEndDate(source.getEndDate());
        project.setName(source.getName());
        project.setPriority(source.getPriority());
        project.setStatus(source.getStatus());

        if (!CollectionUtils.isEmpty(source.getTasks())) {
            project.setTasks(
                    source.getTasks()
                            .stream()
                            .map(taskDtoConverter::convert)
                            .collect(Collectors.toSet())
            );
        }
        return project;
    }
}
