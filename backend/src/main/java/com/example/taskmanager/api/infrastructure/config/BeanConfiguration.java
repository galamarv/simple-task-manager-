package com.example.taskmanager.api.infrastructure.config;

import com.example.taskmanager.api.application.port.in.TaskUseCase;
import com.example.taskmanager.api.application.port.out.TaskRepositoryPort;
import com.example.taskmanager.api.application.service.TaskService;
import com.example.taskmanager.api.infrastructure.persistence.adapter.TaskRepositoryAdapter;
import com.example.taskmanager.api.infrastructure.persistence.jpa.JpaTaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepository jpaTaskRepository) {
        return new TaskRepositoryAdapter(jpaTaskRepository);
    }

    @Bean
    public TaskUseCase taskUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new TaskService(taskRepositoryPort);
    }
}