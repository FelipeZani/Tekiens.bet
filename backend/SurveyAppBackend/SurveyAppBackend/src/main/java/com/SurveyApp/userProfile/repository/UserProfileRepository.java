package com.SurveyApp.userProfile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.SurveyApp.userProfile.model.User;

@Repository
public interface UserProfileRepository extends JpaRepository<User, Long> {

    @NativeQuery("SELECT email FROM user WHERE email=?1")
    public String queryEmail(String email);

    @NativeQuery("SELECT * FROM user where email =?1")
    public Optional<User> getByLoginData(String email);


    
} 