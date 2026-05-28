package com.SurveyApp.surveyManagement;

public enum SurveyStatus {
    OPEN,
    CLOSED,
    RESOLVED;;
    
    @Override
    public String toString() {
        switch (this) {
            case OPEN:
                return "OPEN";
                
            case CLOSED:
                return "CLOSED";
            case RESOLVED:
                return "RESOLVED";
            default:
                return null;
        }
    }

}
