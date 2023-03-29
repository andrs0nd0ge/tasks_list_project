package com.tasklist.project.service;

import com.tasklist.project.dao.UserDao;
import com.tasklist.project.dto.UserDto;
import com.tasklist.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
    public UserDto getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email).orElse(null);
        if (user != null) {
            return UserDto.from(user);
        }
        return null;
    }

    public void registerUser(String username, String email, String password) {
        List<User> users = userDao.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                System.out.println("User already exists");
                break;
            } if (i++ == users.size() - 1 && !users.get(i).getEmail().equals(email)) {
                password = encoder.encode(password);
                userDao.registerUser(username, email, password);
                System.out.println("User was registered successfully");
                break;
            }
        }
    }
}
