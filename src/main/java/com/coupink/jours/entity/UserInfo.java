package com.coupink.jours.entity;

import com.coupink.jours.enums.Sex;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String nickname;
    private LocalDate birthday;
    private Sex sex;
}
