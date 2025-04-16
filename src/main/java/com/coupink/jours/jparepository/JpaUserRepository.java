package com.coupink.jours.jparepository;

import com.coupink.jours.entity.User;
import com.coupink.jours.enums.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    <T> Optional<T> findByProviderAndProviderId(Provider provider, String providerId);
}
