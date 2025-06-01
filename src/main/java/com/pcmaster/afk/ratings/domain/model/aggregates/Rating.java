package com.pcmaster.afk.ratings.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import com.pcmaster.afk.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingCommand;
import com.pcmaster.afk.ratings.domain.model.valueobjects.AdvisoryId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.ProductId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;

@Entity
@Table(name = "ratings")
public class Rating extends AuditableAbstractAggregateRoot<Rating>{

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "type", length = 10, nullable = false)
    private String type;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Getter
    @Min(1)
    @Max(5)
    @Column(name = "punctuation", columnDefinition = "smallint", nullable = false)
    private int punctuation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "user_id")),
    })
    private UserId userId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "productId", column = @Column(name = "product_id")),
    })
    private ProductId productId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "advisoryId", column = @Column(name = "advisory_id")),
    })
    private AdvisoryId advisoryId;


    public Rating(String type, String description, int punctuation, Long userId, Long productId, Long advisoryId){
        this.type = type;
        this.description = description;
        this.punctuation = punctuation;
        this. userId = new UserId(userId);
        this.productId = new ProductId(productId);
        this.advisoryId = new AdvisoryId(advisoryId);
    }

    public Rating(){}

    public Rating(UserId userId, ProductId productId, AdvisoryId advisoryId){
        this();
        this.userId = userId;
        this.productId = productId;
        this.advisoryId = advisoryId;
    }

    public Long getUserId(){
        return userId.userId();
    }

    public Long getProductId(){
        return productId.productId();
    }

    public Long getAdvisoryId(){
        return advisoryId.advisoryId();
    }

    /**
     * Command implementation
     */
    public Rating(CreateRatingCommand command){
        this.type = command.type();
        this.description = command.description();
        this.punctuation = command.punctuation();
        this. userId = new UserId(command.userId());
        this.productId = new ProductId(command.productId());
        this.advisoryId = new AdvisoryId(command.advisoryId());
    }
}
