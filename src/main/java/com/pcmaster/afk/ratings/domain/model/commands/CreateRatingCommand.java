package com.pcmaster.afk.ratings.domain.model.commands;

public record CreateRatingCommand(
        String type,
        String description,
        int punctuation,
        Long userId,
        Long productId,
        Long advisoryId
) {
}
