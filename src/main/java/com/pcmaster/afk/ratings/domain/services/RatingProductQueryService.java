package com.pcmaster.afk.ratings.domain.services;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingProduct;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsProductQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingProductByIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingProductByUserIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingProductByProductIdQuery;

import java.util.List;
import java.util.Optional;

public interface RatingProductQueryService {
    List<RatingProduct> handle(GetAllRatingsProductQuery query);
    Optional<RatingProduct> handle(GetRatingProductByIdQuery query);

    List<RatingProduct> handle(GetAllRatingProductByUserIdQuery query);

    List<RatingProduct> handle(GetAllRatingProductByProductIdQuery query);
}
