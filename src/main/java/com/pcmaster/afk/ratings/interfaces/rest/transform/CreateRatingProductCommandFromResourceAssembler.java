package com.pcmaster.afk.ratings.interfaces.rest.transform;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingProductCommand;
import com.pcmaster.afk.ratings.interfaces.rest.resources.CreateRatingProductResource;

public class CreateRatingProductCommandFromResourceAssembler {
    public static CreateRatingProductCommand toCommandFromResource(CreateRatingProductResource resource){
        return new CreateRatingProductCommand(
                resource.punctuation(),
                resource.description(),
                resource.userId(),
                resource.productId()
        );
    }
}
