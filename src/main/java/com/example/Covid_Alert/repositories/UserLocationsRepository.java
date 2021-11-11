package com.example.Covid_Alert.repositories;

import com.example.Covid_Alert.models.User_Locations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationsRepository extends JpaRepository<User_Locations,Long> {
}
