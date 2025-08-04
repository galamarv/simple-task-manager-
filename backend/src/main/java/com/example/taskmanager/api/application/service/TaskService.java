package com.example.taskmanager.api.application.service;

import com.example.taskmanager.api.application.port.in.TaskUseCase;
import com.example.taskmanager.api.application.port.out.TaskRepositoryPort;
import com.example.taskmanager.api.domain.model.Task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class TaskService implements TaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task createTask(Task task) {
        task.setTaskCode(generateNextTaskCode());
        task.setStatus(Task.Status.TODO);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepositoryPort.save(task);
    }

    @Override
    public Optional<Task> getTaskById(String id) {
        return taskRepositoryPort.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryPort.findAll();
    }

    @Override
    public Optional<Task> updateTask(String id, Task taskDetails) {
        return taskRepositoryPort.findById(id).map(existingTask -> {
            existingTask.setTitle(taskDetails.getTitle());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setStatus(taskDetails.getStatus());
            return taskRepositoryPort.save(existingTask);
        });
    }

    @Override
    public boolean deleteTask(String id) {
        if (!taskRepositoryPort.existsById(id)) {
            return false;
        }
        taskRepositoryPort.deleteById(id);
        return true;
    }

    private synchronized String generateNextTaskCode() {
        String prefix = "T" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String lastCode = taskRepositoryPort.findLastTaskCodeForToday(prefix).orElse(prefix + "0000");
        int nextSequence = Integer.parseInt(lastCode.substring(prefix.length())) + 1;
        return prefix + String.format("%04d", nextSequence);
    }
}