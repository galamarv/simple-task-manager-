package com.example.taskmanager.api.infrastructure.web.dto;

import com.example.taskmanager.api.domain.model.Task;

public record UpdateTaskRequest(String title, String description, Task.Status status) {
}