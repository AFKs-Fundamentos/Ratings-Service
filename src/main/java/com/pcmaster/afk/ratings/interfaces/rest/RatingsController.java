package com.pcmaster.afk.ratings.interfaces.rest;

import com.pcmaster.afk.ratings.domain.model.queries.*;
import com.pcmaster.afk.ratings.domain.model.valueobjects.AdvisoryId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.ProductId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import com.pcmaster.afk.ratings.domain.services.RatingCommandService;
import com.pcmaster.afk.ratings.domain.services.RatingQueryService;
import com.pcmaster.afk.ratings.interfaces.rest.resources.CreateRatingResource;
import com.pcmaster.afk.ratings.interfaces.rest.resources.RatingResource;
import com.pcmaster.afk.ratings.interfaces.rest.transform.CreateRatingCommandFromResourceAssembler;
import com.pcmaster.afk.ratings.interfaces.rest.transform.RatingResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Ratings", description = "Ratings Management Endpoints")
public class RatingsController {

    private final RatingQueryService ratingQueryService;
    private final RatingCommandService ratingCommandService;

    public RatingsController(RatingQueryService ratingQueryService, RatingCommandService ratingCommandService){
        this.ratingQueryService = ratingQueryService;
        this.ratingCommandService = ratingCommandService;
    }

    @Operation(
            summary = "Add new Rating",
            description = "Add a new Rating for PCMaster",
            operationId = "createRating",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateRatingResource.class)
                            )
                    ),
                    @ApiResponse (
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<RatingResource> createRating(@RequestBody CreateRatingResource resource){

        var createRatingCommand = CreateRatingCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var ratingId = this.ratingCommandService.handle(createRatingCommand);

        if(ratingId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }

        var getRatingByIdQuery = new GetRatingByIdQuery(ratingId);
        var optionalService = this.ratingQueryService.handle(getRatingByIdQuery);

        var ratingResource = RatingResourceFromEntityAssembler.toResourceFromEntity(optionalService.get());
        return new ResponseEntity<>(ratingResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch all Ratings",
            description = "Fetch all Ratings created",
            operationId = "getRatings",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RatingResource.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<RatingResource>> getAllRatings() {
        var getAllRatingsQuery = new GetAllRatingsQuery();
        var ratings = this.ratingQueryService.handle(getAllRatingsQuery);
        var ratingsResources = ratings.stream()
                .map(RatingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ratingsResources);
    }

    @GetMapping("/product")
    public ResponseEntity<List<RatingResource>> getByProductId(@RequestParam(name = "productId") Long pId){

        if (pId == null ) {
            return ResponseEntity.badRequest().build();
        }

        ProductId productId = new ProductId(pId);

        var getRatingsByPIdQuery = new GetRatingsByProductIdQuery(productId);
        var ratings = this.ratingQueryService.handle(getRatingsByPIdQuery);

        var ratingsResources = ratings.stream()
                .map(RatingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingsResources);
    }

    @GetMapping("/user")
    public ResponseEntity<List<RatingResource>> getByUserId(@RequestParam(name = "userId") Long uId){

        if (uId == null ) {
            return ResponseEntity.badRequest().build();
        }

        UserId userId = new UserId(uId);

        var getRatingsByUserIdQuery = new GetRatingsByUserIdQuery(userId);
        var ratings = this.ratingQueryService.handle(getRatingsByUserIdQuery);

        var ratingsResource = ratings.stream()
                .map(RatingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingsResource);
    }

    @GetMapping("/advisory")
    public ResponseEntity<List<RatingResource>> getByAdvisoryId(@RequestParam(name = "advisoryId") Long aId){

        if (aId == null ) {
            return ResponseEntity.badRequest().build();
        }

        AdvisoryId advisoryId = new AdvisoryId(aId);

        var getRatingsByAdvisoryIdQuery = new GetRatingsByAdvisoryIdQuery(advisoryId);
        var ratings = this.ratingQueryService.handle(getRatingsByAdvisoryIdQuery);

        var ratingsResource = ratings.stream()
                .map(RatingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingsResource);
    }
}
