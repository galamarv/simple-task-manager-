package com.example.taskmanager.api.infrastructure.persistence.adapter;

import com.example.taskmanager.api.application.port.out.TaskRepositoryPort;
import com.example.taskmanager.api.domain.model.Task;
import com.example.taskmanager.api.infrastructure.persistence.jpa.JpaTaskRepository;
import com.example.taskmanager.api.infrastructure.persistence.mapper.TaskPersistenceMapper;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final JpaTaskRepository jpaTaskRepository;

    @Override
    public Task save(Task task) {
        var taskEntity = TaskPersistenceMapper.toEntity(task);
        var savedEntity = jpaTaskRepository.save(taskEntity);
        return TaskPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Task> findById(String id) {
        return jpaTaskRepository.findById(id).map(TaskPersistenceMapper::toDomain);
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll().stream()
                .map(TaskPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<String> findLastTaskCodeForToday(String prefix) {
        return jpaTaskRepository.findLastTaskCodeForToday(prefix);
    }

    @Override
    public void deleteById(String id) {
        jpaTaskRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return jpaTaskRepository.existsById(id);
    }
}