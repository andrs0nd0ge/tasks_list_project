package com.tasklist.project.dao;

import com.tasklist.project.dto.MakeTaskDto;
import com.tasklist.project.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskDao {
    private final JdbcTemplate jdbcTemplate;
    public List<Task> getAllTasks() {
        String sql = "select * from tasks";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class));
    }
    public Optional<Task> getTaskById(Long id) {
        String sql = String.format("select * from tasks where id = %s", id);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class))
                .stream()
                .findFirst();
    }
    public List<Task> getTasksOfUser(Long userId) {
        String sql = String.format("select * from tasks where user_id = %s", userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class));
    }

    public Optional<Task> getTaskOfUserById(Long taskId, Long userId) {
        String sql = String.format("select * from tasks where id = %s and user_id = %s", taskId, userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class))
                .stream()
                .findFirst();
    }

    public void changeTaskStatus(Long statusId, Long userId, Long taskId) {
        String sql = "update tasks set status_id = ? where user_id = ? and id = ?";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, statusId);
            ps.setLong(2, userId);
            ps.setLong(3, taskId);
            return ps;
        });
    }

    public void createTask(MakeTaskDto task, Long userId) {
        String sql = "insert into tasks (name, description, exe_date, user_id, status_id) " +
                "values (?, ?, ?, ?, 1)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setDate(3, Date.valueOf(task.getDate()));
            ps.setLong(4, userId);
            return ps;
        });
    }
}
