package com.edu.weatherListener.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edu.weatherListener.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
