package com.example.taskmanager.api.infrastructure.persistence.jpa;

import com.example.taskmanager.api.infrastructure.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JpaTaskRepository extends JpaRepository<TaskEntity, String> {
    @Query(value = "SELECT TOP 1 task_code FROM tasks WHERE task_code LIKE ?1% ORDER BY task_code DESC", nativeQuery = true)
    Optional<String> findLastTaskCodeForToday(String prefix);
}