package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingAdvisoryCommand;

public interface RatingAdvisoryCommandService {
    Long handle(CreateRatingAdvisoryCommand command);
}
