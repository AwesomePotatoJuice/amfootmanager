package ru.surin.amfootmanager.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.JmixEntity;
import ru.surin.amfootmanager.BaseUuidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@JmixEntity
@Table(name = "AFM_PLAYER_CARD", indexes = {
        @Index(name = "IDX_PLAYERCARD_PROFILE_ID", columnList = "PROFILE_ID")
})
@Entity(name = "afm_PlayerCard")
public class PlayerCard extends BaseUuidEntity {
    @Column(name = "IMAGE")
    private byte[] image;

    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "PROFILE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}