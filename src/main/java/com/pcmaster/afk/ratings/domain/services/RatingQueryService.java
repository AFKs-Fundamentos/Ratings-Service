package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.aggregates.Rating;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RatingQueryService {
    List<Rating> handle(GetAllRatingsQuery query);
    Optional<Rating> handle(GetRatingByIdQuery query);
}
