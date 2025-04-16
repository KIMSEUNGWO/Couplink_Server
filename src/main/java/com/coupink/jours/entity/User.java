package com.coupink.jours.entity;

import com.coupink.jours.enums.Provider;
import com.coupink.jours.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class User extends CreateEntity {

    @Id
    private Long id;

    private String email;

    @Embedded
    private UserInfo userInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;
    private String providerId;

}