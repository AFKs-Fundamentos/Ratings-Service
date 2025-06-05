package com.pcmaster.afk.ratings.interfaces.rest.transform;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingUser;
import com.pcmaster.afk.ratings.interfaces.rest.resources.RatingUserResource;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class RatingUserResourceFromEntityAssembler {
    public static RatingUserResource toResourceFromEntity(RatingUser entity){

        LocalDate dateLocal = Instant.ofEpochMilli(entity.getCreatedAt().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        return new RatingUserResource(
                entity.getId(),
                entity.getPunctuation(),
                entity.getDescription(),
                entity.getUserId(),
                entity.getTechnicalId(),
                dateLocal.toString()
        );
    }
}
