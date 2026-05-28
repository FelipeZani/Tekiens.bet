package com.SurveyApp.surveyManagement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.SurveyApp.surveyManagement.SurveyStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date closedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    
    private SurveyStatus status;
    @Column(nullable = false)
    private Set<String> tags = new HashSet<>();

    public Survey(Date createdAt, Date closedAt, Set<String> tags, SurveyStatus status) {

        if (createdAt == null)
            throw new IllegalArgumentException("Creation date shouldnt be null");

        if (closedAt == null)
            throw new IllegalArgumentException("Closing date shouldn't be null");
        
        if (tags == null)
            throw new IllegalArgumentException("Tag set shouldn't be null");
        if (status == null)
            throw new IllegalArgumentException("Status shouldn't be null");


        Long dayInMs = 24L * 60 * 60 * 1000;
        
        Date yesterday = new Date(createdAt.getTime() - dayInMs);
        Date tomorow = new Date(createdAt.getTime() + dayInMs);
        Date aMonth = new Date(closedAt.getTime()+dayInMs*30);

        

        if (createdAt.before(yesterday)|| createdAt.after(tomorow) )
            throw new IllegalArgumentException("Creation date exceeded the limit");

        if (closedAt.before(yesterday) || closedAt.after(aMonth))
            throw new IllegalArgumentException("Closing date exceeded the limit");
        if(closedAt.compareTo(createdAt) < 0)
                throw new IllegalArgumentException("Closing date and creation date should be at least a month apart");

        

        for (String string : tags) {
            if (string.isBlank())
                throw new IllegalArgumentException("Tag string shouldn't be empty");

        }

        this.createdAt = createdAt;
        this.closedAt = closedAt;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((closedAt == null) ? 0 : closedAt.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
        Survey other = (Survey) obj;
        if (id != other.id)
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (closedAt == null) {
            if (other.closedAt != null)
                return false;
        } else if (!closedAt.equals(other.closedAt))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Survey [id=" + id + ", createdAt=" + createdAt + ", closedAt=" + closedAt + ", tags=" + tags + "]";
    }

}
