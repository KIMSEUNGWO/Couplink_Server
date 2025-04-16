package com.coupink.jours.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class LastModifiedEntity {

    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
}
