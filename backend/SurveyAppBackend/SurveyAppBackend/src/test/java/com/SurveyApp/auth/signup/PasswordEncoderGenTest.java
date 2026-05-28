package com.SurveyApp.auth.signup;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.SurveyApp.userProfile.userSignUp.PasswordEncoderGenerator;

@SpringBootTest
public class PasswordEncoderGenTest {
    @Test
    public void nullArgsTest() throws Exception{

        assertThrows(NullPointerException.class,()-> PasswordEncoderGenerator.HashPassword(null));

    }
    @Test
    public void blankArgsTest(){
        assertThrows(IllegalArgumentException.class,()->PasswordEncoderGenerator.HashPassword(" "));
    }
   
}
