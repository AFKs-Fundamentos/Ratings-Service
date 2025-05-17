package com.pcmaster.afk.ratings.application.internal.commandservices;

import com.pcmaster.afk.ratings.domain.model.aggregates.Rating;
import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingCommand;
import com.pcmaster.afk.ratings.domain.model.commands.DeleteRatingCommand;
import com.pcmaster.afk.ratings.domain.services.RatingCommandService;
import com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingCommandServiceImpl implements RatingCommandService {

    private final RatingRepository ratingRepository;

    public RatingCommandServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Long handle(CreateRatingCommand command) {

        var rating = new Rating(command);
        try {
            this.ratingRepository.save(rating);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving rating: " + e.getMessage());
        }

        return rating.getId();
    }

    @Override
    public void handle(DeleteRatingCommand command) {

        try{
            this.ratingRepository.deleteById(command.ratingId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting Rating: " + e.getMessage());
        }

    }
}
