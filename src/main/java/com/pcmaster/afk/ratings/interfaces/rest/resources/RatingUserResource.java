package com.pcmaster.afk.ratings.interfaces.rest.resources;

public record RatingUserResource(
        Long id,
        int punctuation,
        String description,
        Long userId,
        Long technicalId,
        String createdAt
) {
}
