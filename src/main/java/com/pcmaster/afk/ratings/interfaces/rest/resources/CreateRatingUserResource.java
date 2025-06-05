package com.pcmaster.afk.ratings.interfaces.rest.resources;

public record CreateRatingUserResource(
        int punctuation,
        String description,
        Long userId,
        Long technicalId
) {
}
