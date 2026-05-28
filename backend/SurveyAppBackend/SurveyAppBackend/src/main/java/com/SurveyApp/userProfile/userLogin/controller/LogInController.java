package com.SurveyApp.userProfile.userLogin.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SurveyApp.userProfile.common.exception.GlobalExceptionHandler;
import com.SurveyApp.userProfile.jwtTokens.JwtToken;
import com.SurveyApp.userProfile.model.User;
import com.SurveyApp.userProfile.repository.UserProfileRepository;
import com.SurveyApp.userProfile.userLogin.model.UserLoginDTO;
import com.SurveyApp.userProfile.userSignUp.PasswordEncoderGenerator;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/session")
public class LogInController {

    @Autowired
    UserProfileRepository repo;

    @Autowired
    GlobalExceptionHandler exceptionHandler;

    
    @GetMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public String userLogin(@RequestBody String body){
        GsonBuilder gs = new GsonBuilder();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			
			System.out.println("Verifing body arguments integrity in User Login");
			System.out.println("Body Request Received"+body);

            
			if (body == null)
				throw new IllegalArgumentException("Body request can't be null");
			if (body.isBlank())
				throw new IllegalArgumentException("Body request can't be blank");

            UserLoginDTO dto = gs.create().fromJson(body,UserLoginDTO.class);
            //verifier les champs
            System.out.println("Hashing pwd for Login...");
            String hash = PasswordEncoderGenerator.HashPassword(dto.getPassword());
            System.out.println("Fetching User...");
            Optional<User> gotUser = repo.getByLoginData(dto.getEmail());
            
            if(gotUser.isEmpty() || passwordEncoder.matches(gotUser.get().getPassword(),hash)){
               throw new NullPointerException("Invalid credentials");
            }
            System.out.println("Credentials are valid");
            
            User myUser = gotUser.get();

			String token = JwtToken.generateLoginToken(myUser.getId(), myUser.getName(),
			myUser.getEmail());
            System.out.println("Returning User...");
            return "{token: "+token+", userData: "+myUser.toJsonString()+"}";

		
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			ResponseEntity<Map<String,Object>> responseEntity = exceptionHandler.handleEmptyRequestBody(e);
			return gs.create().toJson(responseEntity);

        }catch (Exception e) {
			e.printStackTrace();
			ResponseEntity<Map<String,Object>> responseEntity = exceptionHandler.handleGenericException(e);
			return gs.create().toJson(responseEntity);
		}

        
    }

}
