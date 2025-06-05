package com.pcmaster.afk.ratings.domain.model.aggregates;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingAdvisoryCommand;
import com.pcmaster.afk.ratings.domain.model.valueobjects.AdvisoryId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import com.pcmaster.afk.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "rating_advisory")
public class RatingAdvisory extends AuditableAbstractAggregateRoot<RatingAdvisory> {

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
            @AttributeOverride(name = "advisoryId", column = @Column(name = "advisory_id")),
    })
    private AdvisoryId advisoryId;

    public RatingAdvisory(int punctuation, String description, Long userId, Long advisoryId){
        this.punctuation = punctuation;
        this.description = description;
        this.userId = new UserId(userId);
        this.advisoryId = new AdvisoryId(advisoryId);
    }

    public RatingAdvisory(){}

    public RatingAdvisory(UserId userId, AdvisoryId advisoryId){
        this();
        this.userId = userId;
        this.advisoryId = advisoryId;
    }

    public Long getUserId(){
        return userId.userId();
    }

    public Long getAdvisoryId(){
        return advisoryId.advisoryId();
    }

    /**
     * Command implementation
     */
    public RatingAdvisory(CreateRatingAdvisoryCommand command){
        this.punctuation = command.punctuation();
        this.description = command.description();
        this.userId = new UserId(command.userId());
        this.advisoryId = new AdvisoryId(command.advisoryId());
    }
}
