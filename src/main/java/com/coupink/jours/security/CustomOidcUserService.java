package com.coupink.jours.security;

import com.coupink.jours.entity.User;
import com.coupink.jours.enums.Provider;
import com.coupink.jours.enums.Role;
import com.coupink.jours.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    private final UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Provider provider = Provider.findByProvider(userRequest.getClientRegistration().getClientName());
        DefaultOAuth2User convert = provider.convert(oAuth2User);

        OidcUser oidcUser = super.loadUser(userRequest);

        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();


        User user = userRepository.findByProviderAndProviderId(provider, convert.providerId())
                .orElseGet(() -> userRepository.save(User.builder()
                        .role(Role.USER)
                        .email(convert.email())
                        .provider(provider)
                        .providerId(convert.providerId())
                        .build())
                );

        return new OidcUserDetails(
                user,
                Set.of(new SimpleGrantedAuthority(Role.USER.getRoleName())),
                oidcUser.getIdToken(),
                oidcUser.getUserInfo(),
                userNameAttributeName);
    }
}