package com.pcmaster.afk.ratings.interfaces.rest.resources;

public record RatingResource(
        Long id,
        String type,
        String description,
        int punctuation,
        Long userId,
        Long productId,
        Long advisoryId
) {
}
