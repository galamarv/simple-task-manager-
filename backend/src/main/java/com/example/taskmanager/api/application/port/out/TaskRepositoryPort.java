package com.example.taskmanager.api.application.port.out;

import com.example.taskmanager.api.domain.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(String id);
    List<Task> findAll();
    Optional<String> findLastTaskCodeForToday(String prefix);
    void deleteById(String id);
    boolean existsById(String id);
}