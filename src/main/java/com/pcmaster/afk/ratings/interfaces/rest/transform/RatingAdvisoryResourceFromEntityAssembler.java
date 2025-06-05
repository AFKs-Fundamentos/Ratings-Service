package com.pcmaster.afk.ratings.interfaces.rest.transform;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingAdvisory;
import com.pcmaster.afk.ratings.interfaces.rest.resources.RatingAdvisoryResource;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class RatingAdvisoryResourceFromEntityAssembler {
    public static RatingAdvisoryResource toResourceFromEntity(RatingAdvisory entity){

        LocalDate dateLocal = Instant.ofEpochMilli(entity.getCreatedAt().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        return new RatingAdvisoryResource(
                entity.getId(),
                entity.getPunctuation(),
                entity.getDescription(),
                entity.getUserId(),
                entity.getAdvisoryId(),
                dateLocal.toString()
        );
    }
}
