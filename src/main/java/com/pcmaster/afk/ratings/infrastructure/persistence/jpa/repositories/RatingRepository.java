package com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories;

import com.pcmaster.afk.ratings.domain.model.aggregates.Rating;
import com.pcmaster.afk.ratings.domain.model.valueobjects.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByProductId(ProductId productId);
}
