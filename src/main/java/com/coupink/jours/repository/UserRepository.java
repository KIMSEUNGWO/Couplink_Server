package com.coupink.jours.repository;

import com.coupink.jours.entity.User;
import com.coupink.jours.enums.Provider;
import com.coupink.jours.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JpaUserRepository jpaUserRepository;


    public Optional<User> findByProviderAndProviderId(Provider provider, String providerId) {
        return jpaUserRepository.findByProviderAndProviderId(provider, providerId);
    }
    public User save(User user) {
        return jpaUserRepository.save(user);
    }
}
