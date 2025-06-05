package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingUserCommand;

public interface RatingUserCommandService {
    Long handle(CreateRatingUserCommand command);
}
