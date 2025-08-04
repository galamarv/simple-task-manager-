package com.example.taskmanager.api.infrastructure.web.dto;

import com.example.taskmanager.api.domain.model.Task;
import java.time.LocalDateTime;

public record TaskResponse(String taskCode, String title, String description, Task.Status status, LocalDateTime createdAt) {
}