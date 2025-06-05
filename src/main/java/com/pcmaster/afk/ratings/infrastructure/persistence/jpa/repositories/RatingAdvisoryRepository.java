package com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingAdvisory;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingAdvisoryRepository extends JpaRepository<RatingAdvisory, Long> {
    List<RatingAdvisory> findByUserId(UserId userId);
}
