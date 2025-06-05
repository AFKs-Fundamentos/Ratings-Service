package com.pcmaster.afk.ratings.interfaces.rest.transform;

import com.pcmaster.afk.ratings.domain.model.commands.CreateRatingUserCommand;
import com.pcmaster.afk.ratings.interfaces.rest.resources.CreateRatingUserResource;

public class CreateRatingUserCommandFromResourceAssembler {
    public static CreateRatingUserCommand toCommandFromResource(CreateRatingUserResource resource){
        return new CreateRatingUserCommand(
                resource.punctuation(),
                resource.description(),
                resource.userId(),
                resource.technicalId()
        );
    }
}
