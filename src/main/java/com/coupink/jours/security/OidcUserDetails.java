package com.coupink.jours.security;

import com.coupink.jours.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;

public class OidcUserDetails extends DefaultOidcUser {

    private final User user;

    public OidcUserDetails(User user, Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken, OidcUserInfo userInfo, String nameAttributeKey) {
        super(authorities, idToken, userInfo, nameAttributeKey);
        this.user = user;
    }

}