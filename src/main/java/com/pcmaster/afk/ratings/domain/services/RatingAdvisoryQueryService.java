package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingAdvisory;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingAdvisoryByAdvisoryIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingAdvisoryByUserIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsAdvisoryQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingAdvisoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RatingAdvisoryQueryService {
    List<RatingAdvisory> handle(GetAllRatingsAdvisoryQuery query);
    Optional<RatingAdvisory> handle(GetRatingAdvisoryByIdQuery query);

    List<RatingAdvisory> handle(GetAllRatingAdvisoryByUserIdQuery query);

    List<RatingAdvisory> handle(GetAllRatingAdvisoryByAdvisoryIdQuery query);
}
