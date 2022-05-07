package ru.surin.amfootmanager.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@JmixEntity
@Entity(name = "afm_Training")
public class Training extends Event {
    @Column(name = "TRAINING_PROGRAM")
    private String training_program;

    public String getTraining_program() {
        return training_program;
    }

    public void setTraining_program(String training_program) {
        this.training_program = training_program;
    }


}