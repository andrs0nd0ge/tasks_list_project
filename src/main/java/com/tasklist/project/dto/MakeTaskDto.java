package com.tasklist.project.dto;

import com.tasklist.project.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeTaskDto {
    public static MakeTaskDto from(Task task) {
        return builder()
                .name(task.getName())
                .description(task.getDescription())
                .date(task.getExeDate())
                .build();
    }

    private String name;
    private String description;
    private LocalDate date;
}
