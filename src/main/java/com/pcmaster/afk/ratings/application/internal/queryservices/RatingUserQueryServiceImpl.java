package com.pcmaster.afk.ratings.application.internal.queryservices;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingUser;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingUserByTechnicalIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsTechByUserIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsUserQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingUserByIdQuery;
import com.pcmaster.afk.ratings.domain.services.RatingUserQueryService;
import com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories.RatingUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingUserQueryServiceImpl implements RatingUserQueryService {

    private final RatingUserRepository ratingUserRepository;

    public RatingUserQueryServiceImpl(RatingUserRepository ratingUserRepository){
        this.ratingUserRepository = ratingUserRepository;
    }

    @Override
    public List<RatingUser> handle(GetAllRatingsUserQuery query) {
        return this.ratingUserRepository.findAll();
    }

    @Override
    public Optional<RatingUser> handle(GetRatingUserByIdQuery query) {
        return this.ratingUserRepository.findById(query.ratingUserId());
    }

    @Override
    public List<RatingUser> handle(GetAllRatingsTechByUserIdQuery query) {
        return this.ratingUserRepository.findByUserId(query.userId());
    }

    @Override
    public List<RatingUser> handle(GetAllRatingUserByTechnicalIdQuery query) {
        return this.ratingUserRepository.findByTechnicalId(query.technicalId());
    }
}
