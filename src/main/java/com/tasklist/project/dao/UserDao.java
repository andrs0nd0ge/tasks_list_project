package com.tasklist.project.dao;

import com.tasklist.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    public List<User> getAllUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public Optional<User> getUserByEmail(String email) {
        String sql = String.format("select * from users where email like lower('%%%s%%')", email);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findFirst();
    }
    public void registerUser(String username, String email, String password) {
        String sql = String.format("insert into users (username, email, password) " +
                "values ('%s', '%s', '%s')", username, email, password);
        jdbcTemplate.update(sql);
    }
}
