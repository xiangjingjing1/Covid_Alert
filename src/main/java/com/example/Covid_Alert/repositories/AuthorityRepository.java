package com.example.Covid_Alert.repositories;

import com.example.Covid_Alert.models.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authorities,Long> {

}
