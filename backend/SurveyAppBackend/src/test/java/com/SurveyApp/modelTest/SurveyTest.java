package com.SurveyApp.modelTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.SurveyApp.surveyManagement.SurveyStatus;
import com.SurveyApp.surveyManagement.model.Survey;

public class SurveyTest {
    /**
     * Creation date != null
     * Closing date creationDate +30 
     * Tags != null
     */
    @Test public void constructorTest(){

        Survey surv = new Survey( new Date(), new Date(new Date().getTime()+30*24L * 60 * 60 * 1000), new HashSet<String>(), SurveyStatus.OPEN);

        assertAll("Testing survey constructor ",
            () -> assertNotNull(surv.getCreatedAt(),"Creation date shouldn't be null"),
            () -> assertNotNull(surv.getClosedAt(),"Closing date shouldn't be null"),
            () -> assertNotNull(surv.getTags(),"Tags shouldn't be null")
        );
    }
    
}
