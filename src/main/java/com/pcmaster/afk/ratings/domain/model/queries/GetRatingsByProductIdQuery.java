package com.pcmaster.afk.ratings.domain.model.queries;

import com.pcmaster.afk.ratings.domain.model.valueobjects.ProductId;

public record GetRatingsByProductIdQuery(ProductId productId) {
}
