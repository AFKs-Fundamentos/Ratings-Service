package com.pcmaster.afk.ratings.application.internal.queryservices;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingAdvisory;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingAdvisoryByAdvisoryIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingAdvisoryByUserIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsAdvisoryQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingAdvisoryByIdQuery;
import com.pcmaster.afk.ratings.domain.services.RatingAdvisoryQueryService;
import com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories.RatingAdvisoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingAdvisoryQueryServiceImpl implements RatingAdvisoryQueryService {

    private final RatingAdvisoryRepository ratingAdvisoryRepository;

    public RatingAdvisoryQueryServiceImpl(RatingAdvisoryRepository ratingAdvisoryRepository){
        this.ratingAdvisoryRepository = ratingAdvisoryRepository;
    }

    @Override
    public List<RatingAdvisory> handle(GetAllRatingsAdvisoryQuery query) {
        return this.ratingAdvisoryRepository.findAll();
    }

    @Override
    public Optional<RatingAdvisory> handle(GetRatingAdvisoryByIdQuery query) {
        return this.ratingAdvisoryRepository.findById(query.ratingAdvisoryId());
    }

    @Override
    public List<RatingAdvisory> handle(GetAllRatingAdvisoryByUserIdQuery query) {
        return this.ratingAdvisoryRepository.findByUserId(query.userId());
    }

    @Override
    public List<RatingAdvisory> handle(GetAllRatingAdvisoryByAdvisoryIdQuery query) {
        return this.ratingAdvisoryRepository.findByAdvisoryId(query.advisoryId());
    }
}
