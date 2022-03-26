package com.akvelon.task.tracker.controller;

import com.akvelon.task.tracker.model.dto.ProjectDto;
import com.akvelon.task.tracker.model.dto.ProjectStatusChangeRequest;
import com.akvelon.task.tracker.model.entity.Project;
import com.akvelon.task.tracker.model.enums.ProjectStatus;
import com.akvelon.task.tracker.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/project")
@Api("Functions to manipulate with project")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectController {

    ProjectService projectService;

    @GetMapping
    @ApiOperation("Function to get projects with filter and sort")
    public ResponseEntity<Page<ProjectDto>> getProjects(
            @And({
                    @Spec(path = "name", spec = Like.class),
                    @Spec(path = "priority", params = {"priorityFrom", "priorityTo"}, spec = Between.class),
                    @Spec(path = "startDate", params = {"startDateFrom", "startDateTo"}, spec = Between.class),
                    @Spec(path = "endDate", params = {"endDateFrom", "endDateTo"}, spec = Between.class)
            }) Specification<Project> spec,
            @ApiParam("Statuses of the project for sort")
            @RequestParam(required = false) List<ProjectStatus> statuses,
            @PageableDefault(sort = "priority", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(projectService.getProjects(spec, statuses, pageable));
    }

    @GetMapping("/{projectId}")
    @ApiOperation("Function to get project by id")
    public ResponseEntity<ProjectDto> getProjectById(@ApiParam("ID of project")
                                                     @PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectDtoById(projectId));
    }

    @PostMapping
    @ApiOperation("Function to create project")
    public ResponseEntity<Void> createProject(@ApiParam(value = "Body of the request to crete project", required = true)
                                              @RequestBody @Valid ProjectDto projectDto) {
        projectService.createProject(projectDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{projectId}")
    @ApiOperation("Function to edit project by id")
    public ResponseEntity<Void> editProject(@ApiParam("ID of project")
                                            @PathVariable Long projectId,
                                            @ApiParam(value = "Body of the request to edit project", required = true)
                                            @RequestBody @Valid ProjectDto projectDto) {
        projectService.editProject(projectDto, projectId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{projectId}")
    @ApiOperation("Function to delete project by id")
    public ResponseEntity<Void> deleteProject(@ApiParam("ID of project")
                                              @PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/status")
    @ApiOperation("Function to change project status by list of project ids")
    public ResponseEntity<Void> changeStatus(
            @ApiParam(value = "Body of the request to change project status", required = true)
            @RequestBody @Valid ProjectStatusChangeRequest statusChangeRequest) {
        projectService.changeProjectStatusByIds(statusChangeRequest);
        return ResponseEntity.ok().build();
    }
}
