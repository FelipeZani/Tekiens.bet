package com.SurveyApp.userProfile.common.exception;

import java.security.InvalidKeyException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.SurveyApp.userProfile.userSignUp.EmailAlreadyBeingUsedException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(EmailAlreadyBeingUsedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Map<String, Object>> handleEmailBeingAlreadyUsedExcEntity(EmailAlreadyBeingUsedException err) {
        System.out.println("Sending Creating Response entity for the exception : "+err.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", err.getMessage());


        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ResponseEntity<Map<String, Object>> handleEmptyRequestBody(IllegalArgumentException err) {
        System.out.println("Sending Creating Response entity for the exception : "+err.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", err.getMessage());


        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    } 
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ResponseEntity<Map<String, Object>> handleGenericException(Exception err) {
        System.out.println("Sending Creating Response entity for the exception : "+err.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", err.getMessage());


        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidKeyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ResponseEntity<Map<String, Object>> handleInvalidKeyException(InvalidKeyException err) {
        System.out.println("Sending Creating Response entity for the exception : "+err.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", err.getMessage());


        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    } 

}
