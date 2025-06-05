package com.pcmaster.afk.ratings.application.internal.commandservices;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingUser;
import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingUserCommand;
import com.pcmaster.afk.ratings.domain.services.RatingUserCommandService;
import com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories.RatingUserRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingUserCommandServiceImpl implements RatingUserCommandService {

    private final RatingUserRepository ratingUserRepository;

    public RatingUserCommandServiceImpl(RatingUserRepository ratingUserRepository){
        this.ratingUserRepository = ratingUserRepository;
    }

    @Override
    public Long handle(CreateRatingUserCommand command) {
        var ratingUser = new RatingUser(command);

        try {
            this.ratingUserRepository.save(ratingUser);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving rating User: " + e.getMessage());
        }

        return ratingUser.getUserId();
    }
}
