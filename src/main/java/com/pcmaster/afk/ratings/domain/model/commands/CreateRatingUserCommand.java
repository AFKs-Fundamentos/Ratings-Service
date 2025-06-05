package com.pcmaster.afk.ratings.domain.model.commands;

public record CreateRatingUserCommand(
        int punctuation,
        String description,
        Long userId,
        Long technicalId
) {
}
