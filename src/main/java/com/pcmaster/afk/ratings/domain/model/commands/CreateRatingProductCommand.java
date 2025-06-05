package com.pcmaster.afk.ratings.domain.model.commands;

public record CreateRatingProductCommand(
        int punctuation,
        String description,
        Long userId,
        Long productId
) {
}
