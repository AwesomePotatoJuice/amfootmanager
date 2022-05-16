package ru.surin.amfootmanager.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JmixEntity
@Table(name = "AFM_PROFILE", indexes = {
        @Index(name = "IDX_PROFILE_TEAM_ID", columnList = "TEAM_ID"),
        @Index(name = "IDX_PROFILE_ROLE_ID", columnList = "ROLE"),
        @Index(name = "IDX_PROFILE_USER_ID", columnList = "USER_ID", unique = true)
})
@Entity(name = "afm_Profile")
public class Profile {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    private String name;

    @JoinTable(name = "AFM_PROFILE_STATUS_LINK",
            joinColumns = @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Status> status;

    @JoinTable(name = "AFM_PROFILE_POSITION_LINK",
            joinColumns = @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "POSITION_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Position> position;

    @Column(name = "DEBT")
    private Double debt;

    @OneToMany(mappedBy = "currentOwner")
    private List<Equipment> equipment;

    @JoinTable(name = "AFM_PROFILE_TRAINING_LINK",
            joinColumns = @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TRAINING_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Training> attendance;

    @Column(name = "TRAUMAS")
    private String traumas;

    @Column(name = "ROLE")
    private String role;

    @JoinColumn(name = "USER_ID", unique = true)
    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "TEAM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile")
    private PlayerCard playerCard;

    public PlayerCard getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(PlayerCard playerCard) {
        this.playerCard = playerCard;
    }

    public String getTraumas() {
        return traumas;
    }

    public void setTraumas(String traumas) {
        this.traumas = traumas;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}