package com.pcmaster.afk.ratings.interfaces.rest.resources;

public record RatingAdvisoryResource (
        Long id,
        int punctuation,
        String description,
        Long userId,
        Long advisoryId,
        String createdAt
){
}
