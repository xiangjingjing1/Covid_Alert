package com.example.Covid_Alert.repositories;

import com.example.Covid_Alert.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,String> {
    //VerificationToken getOne(String token);
}
