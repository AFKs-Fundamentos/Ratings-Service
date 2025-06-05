package com.pcmaster.afk.ratings.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TechnicalId(Long technicalId) {
    public TechnicalId {
        if(technicalId == null || technicalId < 0){
            throw new IllegalArgumentException("TechnicalId cannot be null");
        }
    }
}
