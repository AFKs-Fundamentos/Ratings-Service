package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingCommand;
import com.pcmaster.afk.ratings.domain.model.commands.DeleteRatingCommand;

public interface RatingCommandService {
    Long handle(CreateRatingCommand command);
    void handle(DeleteRatingCommand command);
}
