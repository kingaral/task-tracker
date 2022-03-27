package com.akvelon.task.tracker.service.impl;

import com.akvelon.task.tracker.converter.ProjectConverter;
import com.akvelon.task.tracker.converter.ProjectDtoConverter;
import com.akvelon.task.tracker.exception.TaskTrackerException;
import com.akvelon.task.tracker.model.dto.ProjectDto;
import com.akvelon.task.tracker.model.dto.ProjectStatusChangeRequest;
import com.akvelon.task.tracker.model.entity.Project;
import com.akvelon.task.tracker.model.enums.ProjectStatus;
import com.akvelon.task.tracker.repository.ProjectRepository;
import com.akvelon.task.tracker.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {

    //repository
    ProjectRepository projectRepository;

    //converter
    ProjectDtoConverter projectDtoConverter;
    ProjectConverter projectConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void createProject(ProjectDto projectDto) {
        projectRepository.save(projectDtoConverter.convert(projectDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDto> getProjects(Specification<Project> spec, List<ProjectStatus> statuses, Pageable pageable) {

        Specification<Project> projectSpec = Specification.where(spec);

        if (!CollectionUtils.isEmpty(statuses)) {
            Specification<Project> statusSpec = (root, qb, builder) ->
                    root.get("status").in(statuses);
            projectSpec = Specification.where(spec).and(statusSpec);
        }
        return projectRepository.findAll(projectSpec, pageable)
                .map(projectConverter::convert);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void editProject(ProjectDto projectDto, Long projectId) {
        Project project = projectDtoConverter.convert(projectDto);
        project.setId(projectId);

        projectRepository.save(project);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new TaskTrackerException
                                (HttpStatus.NOT_FOUND, "Cannot found project with id:" + projectId)
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ProjectDto getProjectDtoById(Long projectId) {
        return projectConverter.convert(findProjectById(projectId));
    }

    @Override
    @Transactional
    public void changeProjectStatusByIds(ProjectStatusChangeRequest statusChangeRequest) {
        projectRepository.updateTaskStatusByIds(statusChangeRequest.getProjectIds(), statusChangeRequest.getStatus());
    }
}
