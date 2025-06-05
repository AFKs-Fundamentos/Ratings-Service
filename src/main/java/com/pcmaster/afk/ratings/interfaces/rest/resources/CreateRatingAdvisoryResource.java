package com.pcmaster.afk.ratings.interfaces.rest.resources;

public record CreateRatingAdvisoryResource(
        int punctuation,
        String description,
        Long userId,
        Long advisoryId
) {
}
