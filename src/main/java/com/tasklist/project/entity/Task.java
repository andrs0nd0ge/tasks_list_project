package com.tasklist.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Task {
    private Long id;
    private String name;
    private String description;
    private LocalDate exeDate;
    private Long userId;
    private Long statusId;
}
