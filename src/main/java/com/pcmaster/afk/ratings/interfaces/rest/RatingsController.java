package com.pcmaster.afk.ratings.interfaces.rest;

import com.pcmaster.afk.ratings.domain.model.queries.*;
import com.pcmaster.afk.ratings.domain.model.valueobjects.AdvisoryId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.ProductId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import com.pcmaster.afk.ratings.domain.services.RatingProductCommandService;
import com.pcmaster.afk.ratings.domain.services.RatingProductQueryService;
import com.pcmaster.afk.ratings.interfaces.rest.resources.CreateRatingProductResource;
import com.pcmaster.afk.ratings.interfaces.rest.resources.RatingProductResource;
import com.pcmaster.afk.ratings.interfaces.rest.transform.CreateRatingProductCommandFromResourceAssembler;
import com.pcmaster.afk.ratings.interfaces.rest.transform.RatingProductResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/rating/product", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rating Product", description = "Rating Product Management Endpoints")
public class RatingsController {

    private final RatingProductQueryService ratingProductQueryService;
    private final RatingProductCommandService ratingProductCommandService;

    public RatingsController(RatingProductQueryService ratingProductQueryService, RatingProductCommandService ratingProductCommandService){
        this.ratingProductQueryService = ratingProductQueryService;
        this.ratingProductCommandService = ratingProductCommandService;
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
                                    schema = @Schema(implementation = CreateRatingProductResource.class)
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
    public ResponseEntity<RatingProductResource> createRatingProduct(@RequestBody CreateRatingProductResource resource){

        var createRatingProductCommand = CreateRatingProductCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var ratingProductId = this.ratingProductCommandService.handle(createRatingProductCommand);

        if(ratingProductId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }

        var getRatingByIdQuery = new GetRatingProductByIdQuery(ratingProductId);
        var optionalService = this.ratingProductQueryService.handle(getRatingByIdQuery);

        var ratingResource = RatingProductResourceFromEntityAssembler.toResourceFromEntity(optionalService.get());
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
                                    schema = @Schema(implementation = RatingProductResource.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<RatingProductResource>> getAllRatingsProduct() {
        var getAllRatingsQuery = new GetAllRatingsProductQuery();
        var ratings = this.ratingProductQueryService.handle(getAllRatingsQuery);
        var ratingsResources = ratings.stream()
                .map(RatingProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ratingsResources);
    }

    @Operation(
            summary = "Ratings by User",
            description = "Fetch Ratings by User Id",
            operationId = "getByUserId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RatingProductResource.class)
                            )
                    )
            }
    )
    @GetMapping("/user")
    public ResponseEntity<List<RatingProductResource>> getByUserId(@RequestParam(name = "userId") Long uId){

        if (uId == null ) {
            return ResponseEntity.badRequest().build();
        }

        UserId userId = new UserId(uId);

        var getRatingsByUserIdQuery = new GetAllRatingProductByUserIdQuery(userId);
        var ratings = this.ratingProductQueryService.handle(getRatingsByUserIdQuery);

        var ratingsResource = ratings.stream()
                .map(RatingProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingsResource);
    }

}
