package com.SurveyApp.userProfile.userSignUp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
    public static String HashPassword(String password) throws IllegalArgumentException, NullPointerException{
        if(password == null){
            throw new NullPointerException("Password can't be null in Password Ebcoder Generator");
        }
        if(password.isBlank()){
            throw new IllegalArgumentException("Password Initialization went wrong");
        }
        int i = 0;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = "";
        while (i < 10) {
            hashedPassword = passwordEncoder.encode(password);
            i++;


        }
       
        
        return hashedPassword;

        
    }
}
