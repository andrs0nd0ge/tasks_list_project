package com.tasklist.project.util;

import com.tasklist.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
@RequiredArgsConstructor
public class UtilityClass {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder encoder;
    public void createTaskStatusesTable() {
        String sql = "create table if not exists task_statuses " +
                "( " +
                "    id   bigserial " +
                "         primary key" +
                "         unique, " +
                "    name varchar(25) " +
                ");";
        jdbcTemplate.execute(sql);
    }
    public void createUsersTable() {
        String sql = "create table users\n" +
                "( " +
                "    id       bigserial " +
                "        primary key " +
                "        unique, " +
                "    username text, " +
                "    email    text " +
                "        unique, " +
                "    password text " +
                ");";
        jdbcTemplate.execute(sql);
    }
    public void createTasksTable() {
        String sql = "create table tasks " +
                "( " +
                "    id          bigserial " +
                "        primary key " +
                "        unique, " +
                "    name        text, " +
                "    description text," +
                "    exe_date    date, " +
                "    user_id     bigint " +
                "        constraint tasks_user_fk " +
                "            references users (id) " +
                "            on update cascade on delete cascade, " +
                "    status_id   bigint " +
                "        constraint tasks_status_fk " +
                "            references task_statuses (id)" +
                "            on update cascade on delete cascade " +
                ");";
        jdbcTemplate.execute(sql);
    }
    public void insertIntoStatusTable() {
        String sql = "insert into task_statuses (name) " +
                "values ('New'), " +
                "       ('In Progress'), " +
                "       ('Done');";
        jdbcTemplate.execute(sql);
    }
    public void insertIntoUsersTable() {
        User first = new User();
        first.setPassword(encoder.encode("123"));
        User second = new User();
        second.setPassword(encoder.encode("123"));
        User third = new User();
        third.setPassword(encoder.encode("123"));
        User fourth = new User();
        fourth.setPassword(encoder.encode("123"));
        User fifth = new User();
        fifth.setPassword(encoder.encode("123"));
        String sql = "insert into users (username, email, password) " +
                "values ('First', 'onetest@test', ?), " +
                "       ('Second', 'twotest@test', ?), " +
                "       ('Third', 'threetest@test', ?), " +
                "       ('Fourth', 'fourtest@test', ?), " +
                "       ('Fifth', 'fivetest@test', ?);";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, first.getPassword());
            ps.setString(2, second.getPassword());
            ps.setString(3, third.getPassword());
            ps.setString(4, fourth.getPassword());
            ps.setString(5, fifth.getPassword());
            return ps;
        });
    }
    public void insertIntoTasksTable() {
        String sql = "insert into tasks (name, description, exe_date, user_id, status_id) " +
                "values ('Thing to do', 'Test description', '2023-10-09', 3, 1), " +
                "       ('Some assignment', 'Do this and that', '2023-05-29', 2, 1), " +
                "       ('Finish the project', 'Some description', '2023-10-09', 1, 2), " +
                "       ('Go to the business meeting', 'Another description', '2023-03-23', 2, 1), " +
                "       ('Clean your room', 'Desc', '2023-02-09', 1, 3);";
        jdbcTemplate.execute(sql);
    }
    public void deleteTestDataFromUsersTable() {
        String deleteFirst = "delete from users where email = 'onetest@test'";
        String deleteSecond = "delete from users where email = 'twotest@test'";
        String deleteThree = "delete from users where email = 'threetest@test'";
        String deleteFour = "delete from users where email = 'fourtest@test'";
        String deleteFive = "delete from users where email = 'fivetest@test'";
        jdbcTemplate.execute(deleteFirst);
        jdbcTemplate.execute(deleteSecond);
        jdbcTemplate.execute(deleteThree);
        jdbcTemplate.execute(deleteFour);
        jdbcTemplate.execute(deleteFive);
    }
    public void deleteAll() {
        String statusQuery = "delete from task_statuses";
        String userQuery = "delete from users";
        String taskQuery = "delete from tasks";
        jdbcTemplate.execute(statusQuery);
        jdbcTemplate.execute(userQuery);
        jdbcTemplate.execute(taskQuery);
    }
    public void resetAutoIncrementForStatus() {
        String sql = "alter sequence task_statuses_id_seq restart with 1";
        jdbcTemplate.execute(sql);
    }
    public void resetAutoIncrementForUsers() {
        String sql = "alter sequence users_id_seq restart with 1";
        jdbcTemplate.execute(sql);
    }
    public void resetAutoIncrementForTasks() {
        String sql = "alter sequence tasks_id_seq restart with 1";
        jdbcTemplate.execute(sql);
    }
}
