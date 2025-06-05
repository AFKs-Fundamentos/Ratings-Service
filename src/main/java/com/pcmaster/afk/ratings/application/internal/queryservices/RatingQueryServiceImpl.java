package com.pcmaster.afk.ratings.application.internal.queryservices;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingProduct;
import com.pcmaster.afk.ratings.domain.model.queries.*;
import com.pcmaster.afk.ratings.domain.services.RatingProductQueryService;
import com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories.RatingProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingQueryServiceImpl implements RatingProductQueryService {

    private final RatingProductRepository ratingProductRepository;

    public RatingQueryServiceImpl(RatingProductRepository ratingProductRepository){
        this.ratingProductRepository = ratingProductRepository;
    }

    @Override
    public List<RatingProduct> handle(GetAllRatingsProductQuery query) {
        return this.ratingProductRepository.findAll();
    }

    @Override
    public Optional<RatingProduct> handle(GetRatingProductByIdQuery query) {
        return this.ratingProductRepository.findById(query.ratingProductId());
    }

    @Override
    public List<RatingProduct> handle(GetAllRatingProductByUserIdQuery query) {
        return this.ratingProductRepository.findByUserId(query.userId());
    }
}
