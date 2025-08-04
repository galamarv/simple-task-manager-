package com.example.taskmanager.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private String taskCode;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;

    public enum Status {
        TODO, IN_PROGRESS, DONE
    }
}