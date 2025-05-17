package com.pcmaster.afk.ratings.interfaces.rest.transform;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingCommand;
import com.pcmaster.afk.ratings.interfaces.rest.resources.CreateRatingResource;

public class CreateRatingCommandFromResourceAssembler {
    public static CreateRatingCommand toCommandFromResource(CreateRatingResource resource){
        return new CreateRatingCommand(
                resource.type(),
                resource.description(),
                resource.punctuation(),
                resource.userId(),
                resource.productId(),
                resource.advisoryId()
        );
    }
}
