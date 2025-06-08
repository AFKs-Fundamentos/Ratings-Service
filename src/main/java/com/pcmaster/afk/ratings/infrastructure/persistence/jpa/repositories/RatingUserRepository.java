package com.pcmaster.afk.ratings.infrastructure.persistence.jpa.repositories;

import com.pcmaster.afk.ratings.domain.model.aggregates.RatingUser;
import com.pcmaster.afk.ratings.domain.model.valueobjects.TechnicalId;
import com.pcmaster.afk.ratings.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingUserRepository extends JpaRepository<RatingUser, Long> {
    List<RatingUser> findByUserId(UserId userId);

    List<RatingUser> findByTechnicalId(TechnicalId technicalId);
}
