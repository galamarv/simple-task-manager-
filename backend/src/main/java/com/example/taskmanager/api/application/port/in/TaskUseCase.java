package com.example.taskmanager.api.application.port.in;

import com.example.taskmanager.api.domain.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskUseCase {
    Task createTask(Task task);
    Optional<Task> getTaskById(String id);
    List<Task> getAllTasks();
    Optional<Task> updateTask(String id, Task taskDetails);
    boolean deleteTask(String id);
}