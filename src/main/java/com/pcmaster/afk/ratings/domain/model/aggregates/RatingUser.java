package com.pcmaster.afk.ratings.domain.model.aggregates;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingUserCommand;
import com.pcmaster.afk.ratings.domain.model.valueobjects.TechnicalId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import com.pcmaster.afk.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "rating_user")
public class RatingUser extends AuditableAbstractAggregateRoot<RatingUser> {

    @Getter
    @Min(1)
    @Max(5)
    @Column(name = "punctuation", columnDefinition = "smallint", nullable = false)
    private int punctuation;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "user_id")),
    })
    private UserId userId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "technicalId", column = @Column(name = "technical_id")),
    })
    private TechnicalId technicalId;

    public RatingUser(int punctuation, String description, Long userId, Long technicalId){
        this.punctuation = punctuation;
        this.description = description;
        this.userId = new UserId(userId);
        this.technicalId = new TechnicalId(technicalId);
    }

    public RatingUser(){}

    public RatingUser(UserId userId, TechnicalId technicalId){
        this();
        this.userId = userId;
        this.technicalId = technicalId;
    }

    public Long getUserId(){
        return userId.userId();
    }

    public Long getTechnicalId(){
        return technicalId.technicalId();
    }

    /**
     * Command implementation
     */
    public  RatingUser(CreateRatingUserCommand command){
        this.punctuation = command.punctuation();
        this.description = command.description();
        this.userId = new UserId(command.userId());
        this.technicalId = new TechnicalId(command.technicalId());
    }
}
