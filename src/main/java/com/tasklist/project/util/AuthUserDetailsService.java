package com.tasklist.project.util;

import com.tasklist.project.dao.UserDao;
import com.tasklist.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserDao userDao;
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDao.getUserByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User was not found");
        }
        return user.get();
    }
}
