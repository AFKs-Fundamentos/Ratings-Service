package com.pcmaster.afk.ratings.domain.model.commands;

public record CreateRatingAdvisoryCommand(
        int punctuation,
        String description,
        Long userId,
        Long advisoryId
) {
}
