package ru.surin.amfootmanager.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
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

    @Column(name = "BASE_")
    private String base;

    @NotNull
    @InstanceName
    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "DURATION")
    private Date duration;

    @JoinColumn(name = "TEAM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getDuration() {
        return duration;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
    public static LocalDateTime calculatedEndTime(LocalDateTime startDate, Date duration){
        return startDate.plusHours(duration.getHours()).plusMinutes(duration.getMinutes());
    }

}