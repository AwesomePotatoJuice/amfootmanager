package ru.surin.amfootmanager.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "AFM_EVENT", indexes = {
        @Index(name = "IDX_EVENT_TEAM_ID", columnList = "TEAM_ID")
})
@Entity(name = "afm_Event")
public class Event {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @Column(name = "TOPIC")
    private String topic;

    @NotNull
    @Column(name = "TRAINING_PROGRAM")
    private String trainingProgram;

    @InstanceName
    @Column(name = "START_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "DURATION")
    private Date duration;

    @JoinColumn(name = "TEAM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getDuration() {
        return duration;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTrainingProgram() {
        return trainingProgram;
    }

    public void setTrainingProgram(String trainingProgram) {
        this.trainingProgram = trainingProgram;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    @PrePersist
    @PreUpdate
    public void preUpdatingEndDate() {
        endDate = calculatedEndTime(startDate, duration);
    }
    public static Date calculatedEndTime(Date startDate, Date duration){
        return Date.from(startDate.toInstant().plus(duration.getHours(), ChronoUnit.HOURS).plus(duration.getMinutes(),ChronoUnit.MINUTES));
    }
}