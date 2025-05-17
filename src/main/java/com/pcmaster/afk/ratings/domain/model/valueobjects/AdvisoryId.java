package com.pcmaster.afk.ratings.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record AdvisoryId(Long advisoryId) {
    public AdvisoryId{
        if (advisoryId == null || advisoryId < 0){
            throw new IllegalArgumentException("AdvisoryId cannot be null");
        }
    }
}
