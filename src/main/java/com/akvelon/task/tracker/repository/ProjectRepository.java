package com.akvelon.task.tracker.repository;

import com.akvelon.task.tracker.model.entity.Project;
import com.akvelon.task.tracker.model.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Modifying
    @Query("update Project set status =:projectStatus where id in (:projectIds)")
    void updateTaskStatusByIds(List<Long> projectIds, ProjectStatus projectStatus);

}
