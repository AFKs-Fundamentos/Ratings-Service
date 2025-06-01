package com.pcmaster.afk.ratings.application.internal.queryservices;

import com.pcmaster.afk.ratings.domain.model.aggregates.Rating;
import com.pcmaster.afk.ratings.domain.model.queries.*;
import com.pcmaster.afk.ratings.domain.services.RatingQueryService;
import com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingQueryServiceImpl implements RatingQueryService {

    private final RatingRepository ratingRepository;

    public RatingQueryServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> handle(GetAllRatingsQuery query) {
        return this.ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> handle(GetRatingByIdQuery query) {
        return this.ratingRepository.findById(query.ratingId());
    }

    @Override
    public List<Rating> handle(GetRatingsByProductIdQuery query) {
        return this.ratingRepository.findByProductId(query.productId());
    }

    @Override
    public List<Rating> handle(GetRatingsByUserIdQuery query) {
        return this.ratingRepository.findByUserId(query.userId());
    }

    @Override
    public List<Rating> handle(GetRatingsByAdvisoryIdQuery query) {
        return this.ratingRepository.findByAdvisoryId(query.advisoryId());
    }
}
