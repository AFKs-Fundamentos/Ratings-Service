package com.pcmaster.afk.ratings.application.internal.commandservices;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingAdvisory;
import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingAdvisoryCommand;
import com.pcmaster.afk.ratings.domain.services.RatingAdvisoryCommandService;
import com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories.RatingAdvisoryRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingAdvisoryCommandServiceImpl implements RatingAdvisoryCommandService {

    private final RatingAdvisoryRepository ratingAdvisoryRepository;

    public RatingAdvisoryCommandServiceImpl(RatingAdvisoryRepository ratingAdvisoryRepository){
        this.ratingAdvisoryRepository = ratingAdvisoryRepository;
    }

    @Override
    public Long handle(CreateRatingAdvisoryCommand command) {

        var ratingAdvisory = new RatingAdvisory(command);
        try {
            this.ratingAdvisoryRepository.save(ratingAdvisory);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving rating Advisory: " + e.getMessage());
        }

        return ratingAdvisory.getId();
    }
}
