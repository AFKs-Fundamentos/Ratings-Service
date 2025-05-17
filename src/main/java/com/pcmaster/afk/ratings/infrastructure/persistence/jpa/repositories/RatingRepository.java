package com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories;

import com.pcmaster.afk.ratings.domain.model.aggregates.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
