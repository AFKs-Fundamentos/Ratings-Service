package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingUser;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsTechByUserIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsUserQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RatingUserQueryService {
    List<RatingUser> handle(GetAllRatingsUserQuery query);
    Optional<RatingUser> handle(GetRatingUserByIdQuery query);

    List<RatingUser> handle(GetAllRatingsTechByUserIdQuery query);
}
