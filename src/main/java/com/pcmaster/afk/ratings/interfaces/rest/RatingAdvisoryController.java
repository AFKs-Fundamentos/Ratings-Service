package com.pcmaster.afk.ratings.interfaces.rest;

import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingAdvisoryByUserIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsAdvisoryQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingAdvisoryByIdQuery;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import com.pcmaster.afk.ratings.domain.services.RatingAdvisoryCommandService;
import com.pcmaster.afk.ratings.domain.services.RatingAdvisoryQueryService;
import com.pcmaster.afk.ratings.interfaces.rest.resources.CreateRatingAdvisoryResource;
import com.pcmaster.afk.ratings.interfaces.rest.resources.RatingAdvisoryResource;
import com.pcmaster.afk.ratings.interfaces.rest.transform.CreateRatingAdvisoryCommandFromResourceAssembler;
import com.pcmaster.afk.ratings.interfaces.rest.transform.RatingAdvisoryResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/rating/advisory", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rating Advisory", description = "Rating Advisory Management Endpoints")
public class RatingAdvisoryController {

    private final RatingAdvisoryQueryService ratingAdvisoryQueryService;
    private final RatingAdvisoryCommandService ratingAdvisoryCommandService;

    public RatingAdvisoryController(RatingAdvisoryQueryService ratingAdvisoryQueryService, RatingAdvisoryCommandService ratingAdvisoryCommandService){
        this.ratingAdvisoryQueryService = ratingAdvisoryQueryService;
        this.ratingAdvisoryCommandService = ratingAdvisoryCommandService;

    }

    @PostMapping
    public ResponseEntity<RatingAdvisoryResource> createRatingAdvisory(@RequestBody CreateRatingAdvisoryResource resource){
        var createRatingAdvisoryCommand = CreateRatingAdvisoryCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var ratingAdvisoryId = this.ratingAdvisoryCommandService.handle(createRatingAdvisoryCommand);

        if(ratingAdvisoryId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }

        var getRatingByIdQuery = new GetRatingAdvisoryByIdQuery(ratingAdvisoryId);
        var optionalRating = this.ratingAdvisoryQueryService.handle(getRatingByIdQuery);

        var ratingResource = RatingAdvisoryResourceFromEntityAssembler.toResourceFromEntity(optionalRating.get());

        return new ResponseEntity<>(ratingResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RatingAdvisoryResource>> getAllRatingAdvisory() {
        var getAllRatingQuery = new GetAllRatingsAdvisoryQuery();
        var ratings = this.ratingAdvisoryQueryService.handle(getAllRatingQuery);
        var ratingResources = ratings.stream()
                .map(RatingAdvisoryResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ratingResources);
    }

    @GetMapping("/user")
    public ResponseEntity<List<RatingAdvisoryResource>> getAdvisoryByUserId(@RequestParam(name = "userId") Long uId){

        if (uId == null ) {
            return ResponseEntity.badRequest().build();
        }

        UserId userId = new UserId(uId);

        var getRatingsByUserIdQuery = new GetAllRatingAdvisoryByUserIdQuery(userId);
        var ratings = this.ratingAdvisoryQueryService.handle(getRatingsByUserIdQuery);

        var ratingsResource = ratings.stream()
                .map(RatingAdvisoryResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingsResource);
    }
}
