package com.pcmaster.afk.ratings.interfaces.rest.resources;

public record CreateRatingResource(
        String type,
        String description,
        int punctuation,
        Long userId,
        Long productId,
        Long advisoryId
) {
}
