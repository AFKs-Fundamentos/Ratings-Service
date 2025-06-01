package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.aggregates.Rating;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingByIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingsByProductIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingsByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface RatingQueryService {
    List<Rating> handle(GetAllRatingsQuery query);
    Optional<Rating> handle(GetRatingByIdQuery query);

    List<Rating> handle(GetRatingsByProductIdQuery query);

    List<Rating> handle(GetRatingsByUserIdQuery query);
}
