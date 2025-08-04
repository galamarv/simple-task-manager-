package com.example.taskmanager.api.infrastructure.persistence.mapper;

import com.example.taskmanager.api.domain.model.Task;
import com.example.taskmanager.api.infrastructure.persistence.entity.TaskEntity;

public class TaskPersistenceMapper {

    public static TaskEntity toEntity(Task domain) {
        TaskEntity entity = new TaskEntity();
        entity.setTaskCode(domain.getTaskCode());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }

    public static Task toDomain(TaskEntity entity) {
        return new Task(
                entity.getTaskCode(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}