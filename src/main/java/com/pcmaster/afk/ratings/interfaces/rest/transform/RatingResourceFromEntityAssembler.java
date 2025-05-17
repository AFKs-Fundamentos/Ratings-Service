package com.pcmaster.afk.ratings.interfaces.rest.transform;

import com.pcmaster.afk.ratings.domain.model.aggregates.Rating;
import com.pcmaster.afk.ratings.interfaces.rest.resources.RatingResource;

public class RatingResourceFromEntityAssembler {
    public static RatingResource toResourceFromEntity(Rating entity) {
        return new RatingResource(
                entity.getId(),
                entity.getType(),
                entity.getDescription(),
                entity.getPunctuation(),
                entity.getUserId(),
                entity.getProductId(),
                entity.getAdvisoryId()
        );
    }
}
