package com.SurveyApp.surveyManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @Size(min=1)
    private String answerValue;
    
    @ManyToOne
    private Survey survey;

    public Answer(String answerValue, Survey survey) {
       
        this.answerValue = answerValue;
        this.survey = survey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((answerValue == null) ? 0 : answerValue.hashCode());
        result = prime * result + ((survey == null) ? 0 : survey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Answer other = (Answer) obj;
        if (id != other.id)
            return false;
        if (answerValue == null) {
            if (other.answerValue != null)
                return false;
        } else if (!answerValue.equals(other.answerValue))
            return false;
        if (survey == null) {
            if (other.survey != null)
                return false;
        } else if (!survey.equals(other.survey))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", answerValue=" + answerValue + ", survey=" + survey + "]";
    }
    

}
