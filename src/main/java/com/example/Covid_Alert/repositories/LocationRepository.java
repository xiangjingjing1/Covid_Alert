package com.example.Covid_Alert.repositories;

import com.example.Covid_Alert.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
