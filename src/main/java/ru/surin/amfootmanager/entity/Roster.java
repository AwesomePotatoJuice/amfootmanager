package ru.surin.amfootmanager.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import ru.surin.amfootmanager.BaseUuidEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@JmixEntity
@Table(name = "AFM_ROSTER", indexes = {
        @Index(name = "IDX_ROSTER_CENTER_ID", columnList = "CENTER_ID"),
        @Index(name = "IDX_ROSTER_QUARTERBACK_ID", columnList = "QUARTERBACK_ID")
})
@Entity(name = "afm_Roster")
public class Roster extends BaseUuidEntity {

    @JoinColumn(name = "CENTER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Profile center;

    @JoinColumn(name = "QUARTERBACK_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Profile quarterback;

    public Profile getQuarterback() {
        return quarterback;
    }

    public void setQuarterback(Profile quarterback) {
        this.quarterback = quarterback;
    }

    public Profile getCenter() {
        return center;
    }

    public void setCenter(Profile center) {
        this.center = center;
    }

}