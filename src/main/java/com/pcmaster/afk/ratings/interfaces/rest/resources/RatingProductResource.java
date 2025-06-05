package com.pcmaster.afk.ratings.interfaces.rest.resources;

public record RatingProductResource(
        Long id,
        int punctuation,
        String description,
        Long userId,
        Long productId,
        String createdAt
) {
}
