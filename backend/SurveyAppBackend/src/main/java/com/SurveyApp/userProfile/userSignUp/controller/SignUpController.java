package com.SurveyApp.userProfile.userSignUp.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SurveyApp.userProfile.common.exception.GlobalExceptionHandler;
import com.SurveyApp.userProfile.model.User;
import com.SurveyApp.userProfile.userSignUp.EmailAlreadyBeingUsedException;
import com.SurveyApp.userProfile.userSignUp.model.userDTO.UserSingUpDTO;
import com.SurveyApp.userProfile.userSignUp.service.UserSignUpService;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/session")
public class SignUpController {


	@Autowired
	GlobalExceptionHandler exceptionHandler;
	
	@Autowired
	UserSignUpService service;	
	

	@PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
	public String createUser(@RequestBody String body) {
		GsonBuilder gs = new GsonBuilder();

		try {
			
			System.out.println("Verifing body arguments integrity");

		
			if (body == null)
				throw new IllegalArgumentException("Body request can't be null");
			if (body.isBlank())
				throw new IllegalArgumentException("Body request can't be blank");


			UserSingUpDTO myUserDTO = gs.create().fromJson(body, UserSingUpDTO.class);
			User myUser = myUserDTO.toUser();
			
			if(service.mailAlreadyExists(myUser.getEmail())){
				throw new EmailAlreadyBeingUsedException("Email: "+ myUser.getEmail()+", is being already used");

			}

			String jwtToken = service.signUp(myUser);
			
			return jwtToken;

		}catch(EmailAlreadyBeingUsedException e){
			e.printStackTrace();
			ResponseEntity<Map<String,Object>> responseEntity = exceptionHandler.handleEmailBeingAlreadyUsedExcEntity(e);
			return gs.create().toJson(responseEntity);
			

		}catch(IllegalArgumentException e){
			e.printStackTrace();
			ResponseEntity<Map<String,Object>> responseEntity = exceptionHandler.handleEmptyRequestBody(e);
			return gs.create().toJson(responseEntity);

		}catch(java.security.InvalidKeyException e ){
			e.printStackTrace();
			ResponseEntity<Map<String,Object>> responseEntity = exceptionHandler.handleInvalidKeyException(e);
			return gs.create().toJson(responseEntity);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseEntity<Map<String,Object>> responseEntity = exceptionHandler.handleGenericException(e);
			return gs.create().toJson(responseEntity);
		}

	}
	


}
