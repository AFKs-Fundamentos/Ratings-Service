package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingProductCommand;
import com.pcmaster.afk.ratings.domain.model.commands.DeleteRatingProductCommand;

public interface RatingProductCommandService {
    Long handle(CreateRatingProductCommand command);
    void handle(DeleteRatingProductCommand command);
}
