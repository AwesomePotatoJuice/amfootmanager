package ru.surin.amfootmanager.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;

@JmixEntity
@Entity(name = "afm_Match")
public class Match extends Event {

    @Column(name = "SCORE")
    private String score;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Team opponent;

    public void setOpponent(Team opponent) {
        this.opponent = opponent;
    }

    public Team getOpponent() {
        return opponent;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}