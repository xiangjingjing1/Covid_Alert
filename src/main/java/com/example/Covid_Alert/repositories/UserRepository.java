package com.example.Covid_Alert.repositories;

import com.example.Covid_Alert.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsUserByUsername(String username);

    User findByUsername(String username);
}
