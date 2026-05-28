package com.SurveyApp.modelTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.SurveyApp.surveyManagement.SurveyStatus;
import com.SurveyApp.surveyManagement.model.Answer;
import com.SurveyApp.surveyManagement.model.Survey;

public class AnswerTest {
    @Test public void constructorTest(){
        Answer ans = new Answer(
            "Dodo",
            new Survey(new Date(), new Date(new Date().getTime()+30*60*60*1000),
            new HashSet<String>(),
            SurveyStatus.RESOLVED));


        assertAll("Test answer constructor", 
            () -> assertNotNull(ans.getAnswerValue()),
            ()-> assertFalse(ans.getAnswerValue().isBlank()),
            ()-> assertNotNull(ans.getSurvey()),
            ()->assertNotNull(ans.getSurvey())
        );
    }
    
}
