package com.example.taskmanager.api.infrastructure.web.controller;

import com.example.taskmanager.api.application.port.in.TaskUseCase;
import com.example.taskmanager.api.infrastructure.web.dto.CreateTaskRequest;
import com.example.taskmanager.api.infrastructure.web.dto.TaskResponse;
import com.example.taskmanager.api.infrastructure.web.dto.UpdateTaskRequest;
import com.example.taskmanager.api.infrastructure.web.mapper.TaskApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173") // Your frontend URL
@RequiredArgsConstructor
public class TaskController {

    private final TaskUseCase taskUseCase;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
        var task = TaskApiMapper.toDomain(request);
        var createdTask = taskUseCase.createTask(task);
        return new ResponseEntity<>(TaskApiMapper.toResponse(createdTask), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        var tasks = taskUseCase.getAllTasks();
        var responses = tasks.stream().map(TaskApiMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable String id) {
        return taskUseCase.getTaskById(id)
                .map(task -> ResponseEntity.ok(TaskApiMapper.toResponse(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable String id, @RequestBody UpdateTaskRequest request) {
        var taskDetails = TaskApiMapper.toDomain(request);
        return taskUseCase.updateTask(id, taskDetails)
                .map(task -> ResponseEntity.ok(TaskApiMapper.toResponse(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        return taskUseCase.deleteTask(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}