package com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingProduct;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingProductRepository extends JpaRepository<RatingProduct, Long> {

    List<RatingProduct> findByUserId(UserId userId);

}
