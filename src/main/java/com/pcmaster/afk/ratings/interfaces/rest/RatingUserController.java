package com.pcmaster.afk.ratings.interfaces.rest;

import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingUserByTechnicalIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsTechByUserIdQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetAllRatingsUserQuery;
import com.pcmaster.afk.ratings.domain.model.queries.GetRatingUserByIdQuery;
import com.pcmaster.afk.ratings.domain.model.valueobjects.TechnicalId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import com.pcmaster.afk.ratings.domain.services.RatingUserCommandService;
import com.pcmaster.afk.ratings.domain.services.RatingUserQueryService;
import com.pcmaster.afk.ratings.interfaces.rest.resources.CreateRatingUserResource;
import com.pcmaster.afk.ratings.interfaces.rest.resources.RatingUserResource;
import com.pcmaster.afk.ratings.interfaces.rest.transform.CreateRatingUserCommandFromResourceAssembler;
import com.pcmaster.afk.ratings.interfaces.rest.transform.RatingUserResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/rating/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rating User", description = "Rating User Management Endpoints")
public class RatingUserController {

    private final RatingUserQueryService ratingUserQueryService;
    private final RatingUserCommandService ratingUserCommandService;

    public RatingUserController(RatingUserQueryService ratingUserQueryService, RatingUserCommandService ratingUserCommandService){
        this.ratingUserQueryService = ratingUserQueryService;
        this.ratingUserCommandService = ratingUserCommandService;
    }

    @Operation(
            summary = "Add new Rating User",
            description = "Add a new rating for technical user",
            operationId = "createRatingUser",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateRatingUserResource.class)
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
    public ResponseEntity<RatingUserResource> createRatingUser(@RequestBody CreateRatingUserResource resource){
        var createRatingUserCommand = CreateRatingUserCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var ratingUserId = this.ratingUserCommandService.handle(createRatingUserCommand);

        if(ratingUserId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }

        var getRatingByIdQuery = new GetRatingUserByIdQuery(ratingUserId);
        var optionalRating = this.ratingUserQueryService.handle(getRatingByIdQuery);

        var ratingResource = RatingUserResourceFromEntityAssembler.toResourceFromEntity(optionalRating.get());

        return new ResponseEntity<>(ratingResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch all Technical Ratings",
            description = "Fetch all Technical Ratings created",
            operationId = "getRatingsTechnical",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RatingUserResource.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<RatingUserResource>> getAllRatingUser() {
        var getAllRatingQuery = new GetAllRatingsUserQuery();
        var ratings = this.ratingUserQueryService.handle(getAllRatingQuery);
        var ratingsResource = ratings.stream()
                .map(RatingUserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ratingsResource);
    }

    @Operation(
            summary = "Technical Ratings by User",
            description = "Fetch Technical Ratings made by User",
            operationId = "getByUserId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RatingUserResource.class)
                            )
                    )
            }
    )
    @GetMapping("/user")
    public ResponseEntity<List<RatingUserResource>> getTechRatingsBYUserId(@RequestParam(name = "userId") Long uId){
        if (uId == null ) {
            return ResponseEntity.badRequest().build();
        }

        UserId userId = new UserId(uId);

        var getRatingByUserIdQuery = new GetAllRatingsTechByUserIdQuery(userId);
        var ratings = this.ratingUserQueryService.handle(getRatingByUserIdQuery);

        var ratingsResource = ratings.stream()
                .map(RatingUserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingsResource);
    }

    @Operation(
            summary = "Ratings by Technical User",
            description = "Fetch Ratings made by Technical User",
            operationId = "getByTechnicalId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RatingUserResource.class)
                            )
                    )
            }
    )
    @GetMapping("/technical")
    public ResponseEntity<List<RatingUserResource>> getRatingUserByTechnicalId(@RequestParam(name = "technicalId") Long tId){
        if (tId == null ) {
            return ResponseEntity.badRequest().build();
        }
        TechnicalId technicalId = new TechnicalId(tId);
        var getRatingsByTechnicalIdQuery = new GetAllRatingUserByTechnicalIdQuery(technicalId);

        var ratings = this.ratingUserQueryService.handle(getRatingsByTechnicalIdQuery);
        var ratingsResource = ratings.stream()
                .map(RatingUserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingsResource);
    }
}
