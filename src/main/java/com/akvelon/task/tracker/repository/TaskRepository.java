package com.akvelon.task.tracker.repository;

import com.akvelon.task.tracker.model.entity.Task;
import com.akvelon.task.tracker.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @Modifying
    @Query("update Task set status =:taskStatus where id in (:taskIds)")
    void updateTaskStatusByIds(List<Long> taskIds, TaskStatus taskStatus);

}
