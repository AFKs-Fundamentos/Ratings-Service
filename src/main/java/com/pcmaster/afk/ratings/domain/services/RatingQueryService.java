package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.aggregates.Rating;
import com.pcmaster.afk.ratings.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface RatingQueryService {
    List<Rating> handle(GetAllRatingsQuery query);
    Optional<Rating> handle(GetRatingByIdQuery query);

    List<Rating> handle(GetRatingsByProductIdQuery query);

    List<Rating> handle(GetRatingsByUserIdQuery query);

    List<Rating> handle(GetRatingsByAdvisoryIdQuery query);
}
