package ru.surin.amfootmanager.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@JmixEntity
@Entity(name = "afm_Training")
public class Training extends Event {
    @Column(name = "TRAINING_PROGRAM")
    private String training_program;

    @JoinTable(name = "AFM_PROFILE_TRAINING_LINK",
            joinColumns = @JoinColumn(name = "TRAINING_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Profile> profiles;

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public String getTraining_program() {
        return training_program;
    }

    public void setTraining_program(String training_program) {
        this.training_program = training_program;
    }


}