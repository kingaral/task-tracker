package com.akvelon.task.tracker.service;

import com.akvelon.task.tracker.model.dto.ProjectDto;
import com.akvelon.task.tracker.model.dto.ProjectStatusChangeRequest;
import com.akvelon.task.tracker.model.entity.Project;
import com.akvelon.task.tracker.model.enums.ProjectStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Functions to control project
 */
public interface ProjectService {

    /**
     * Method to create project
     *
     * @param projectDto {@link ProjectDto}
     */
    void createProject(ProjectDto projectDto);

    /**
     * Method to get list of projects
     *
     * @param spec     Specification to filter data
     * @param statuses List of {@link ProjectStatus}
     * @param pageable pageable for sorting and for pageable
     * @return list of page  {@link  ProjectDto}
     */
    Page<ProjectDto> getProjects(Specification<Project> spec, List<ProjectStatus> statuses, Pageable pageable);

    /**
     * Method to edit list of projects
     *
     * @param projectDto {@link ProjectDto}
     * @param projectId  id of the project {@link Project#getId}
     */
    void editProject(ProjectDto projectDto, Long projectId);

    /**
     * Method for deleting project
     *
     * @param projectId id of the project {@link Project#getId}
     */
    void deleteProject(Long projectId);

    /**
     * Method for finding project by id
     *
     * @param projectId id of the project {@link Project#getId}
     * @return Entity {@link  Project}
     */
    Project findProjectById(Long projectId);

    /**
     * Method for finding projectDto by id
     *
     * @param projectId id of the project {@link Project#getId}
     * @return DTO {@link  ProjectDto}
     */
    ProjectDto getProjectDtoById(Long projectId);

    /**
     * Method for changing task status by list of ids
     *
     * @param statusChangeRequest {@link ProjectStatusChangeRequest}
     */
    void changeProjectStatusByIds(ProjectStatusChangeRequest statusChangeRequest);

}
