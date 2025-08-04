package com.example.taskmanager.api.infrastructure.web.mapper;

import com.example.taskmanager.api.domain.model.Task;
import com.example.taskmanager.api.infrastructure.web.dto.CreateTaskRequest;
import com.example.taskmanager.api.infrastructure.web.dto.TaskResponse;
import com.example.taskmanager.api.infrastructure.web.dto.UpdateTaskRequest;

public class TaskApiMapper {

    public static Task toDomain(CreateTaskRequest dto) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        return task;
    }

    public static Task toDomain(UpdateTaskRequest dto) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        return task;
    }

    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getTaskCode(), task.getTitle(), task.getDescription(), task.getStatus(), task.getCreatedAt());
    }
}