package com.pcmaster.afk.ratings.interfaces.rest.transform;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingAdvisoryCommand;
import com.pcmaster.afk.ratings.interfaces.rest.resources.CreateRatingAdvisoryResource;

public class CreateRatingAdvisoryCommandFromResourceAssembler {
    public static CreateRatingAdvisoryCommand toCommandFromResource(CreateRatingAdvisoryResource resource){
        return new CreateRatingAdvisoryCommand(
                resource.punctuation(),
                resource.description(),
                resource.userId(),
                resource.advisoryId()
        );
    }
}
