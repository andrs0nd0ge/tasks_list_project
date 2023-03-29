package com.tasklist.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GetTaskDto {
    public static GetTaskDto from(Task task) {
        return builder()
                .id(task.getId())
                .name(task.getName())
                .statusId(task.getStatusId())
                .date(task.getExeDate())
                .build();
    }

    private Long id;
    private String name;
    @JsonProperty("status_id")
    private Long statusId;
    private LocalDate date;
}
