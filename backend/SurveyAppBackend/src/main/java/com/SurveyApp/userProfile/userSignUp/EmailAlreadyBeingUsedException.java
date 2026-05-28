package com.SurveyApp.userProfile.userSignUp;

public class EmailAlreadyBeingUsedException extends Exception{
    public EmailAlreadyBeingUsedException(String message){
        super(message);
    }

}
