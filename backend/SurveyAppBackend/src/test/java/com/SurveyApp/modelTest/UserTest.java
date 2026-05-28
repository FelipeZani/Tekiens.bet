package com.SurveyApp.modelTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.SurveyApp.userProfile.model.User;

public class UserTest {


    /*
        name != null and " "
        login != null and " "
        password != and " "
        daily != null
    */
    @Test 
    public void constructorTest(){
        User usr = new User("Robert", "rob123", "123","amancqdsqsd@gmail.com");

        assertAll("Testing user constructor",
            ()-> assertNotNull(usr.getName(),"Username shouldnt be null"),
            ()-> assertNotNull(usr.getLogin(), "Login shouldnt be null"),
            ()-> assertNotNull(usr.getPassword(), "Password shouldnt be null")
            
        );

        
    }
}
