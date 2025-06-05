package com.pcmaster.afk.ratings.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import com.pcmaster.afk.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingProductCommand;
import com.pcmaster.afk.ratings.domain.model.valueobjects.AdvisoryId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.ProductId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;

@Entity
@Table(name = "rating_product")
public class RatingProduct extends AuditableAbstractAggregateRoot<RatingProduct>{

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
            @AttributeOverride(name = "productId", column = @Column(name = "product_id")),
    })
    private ProductId productId;

    public RatingProduct(String description, int punctuation, Long userId, Long productId){
        this.punctuation = punctuation;
        this.description = description;
        this.userId = new UserId(userId);
        this.productId = new ProductId(productId);
    }

    public RatingProduct(){}

    public RatingProduct(UserId userId, ProductId productId){
        this();
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId(){
        return userId.userId();
    }

    public Long getProductId(){
        return productId.productId();
    }

    /**
     * Command implementation
     */
    public RatingProduct(CreateRatingProductCommand command){
        this.punctuation = command.punctuation();
        this.description = command.description();
        this.userId = new UserId(command.userId());
        this.productId = new ProductId(command.productId());
    }
}
