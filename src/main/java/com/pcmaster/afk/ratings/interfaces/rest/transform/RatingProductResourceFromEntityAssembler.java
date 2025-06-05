package com.pcmaster.afk.ratings.interfaces.rest.transform;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingProduct;
import com.pcmaster.afk.ratings.interfaces.rest.resources.RatingProductResource;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class RatingProductResourceFromEntityAssembler {
    public static RatingProductResource toResourceFromEntity(RatingProduct entity) {

        LocalDate dateLocal = Instant.ofEpochMilli(entity.getCreatedAt().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        return new RatingProductResource(
                entity.getId(),
                entity.getPunctuation(),
                entity.getDescription(),
                entity.getUserId(),
                entity.getProductId(),
                dateLocal.toString()
        );
    }
}
