package com.pcmaster.afk.ratings.application.internal.commandservices;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingProduct;
import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingProductCommand;
import com.pcmaster.afk.ratings.domain.model.commands.DeleteRatingProductCommand;
import com.pcmaster.afk.ratings.domain.services.RatingProductCommandService;
import com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories.RatingProductRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingCommandServiceImpl implements RatingProductCommandService {

    private final RatingProductRepository ratingProductRepository;

    public RatingCommandServiceImpl(RatingProductRepository ratingProductRepository){
        this.ratingProductRepository = ratingProductRepository;
    }

    @Override
    public Long handle(CreateRatingProductCommand command) {

        var ratingProduct = new RatingProduct(command);
        try {
            this.ratingProductRepository.save(ratingProduct);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving rating product: " + e.getMessage());
        }

        return ratingProduct.getId();
    }

    @Override
    public void handle(DeleteRatingProductCommand command) {

        try{
            this.ratingProductRepository.deleteById(command.ratingProductId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting Rating Product: " + e.getMessage());
        }

    }
}
