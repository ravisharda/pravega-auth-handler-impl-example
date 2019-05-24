package org.example.pravega.authhandler.plugin;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@Slf4j
@ToString
public class TokenPrincipal implements Principal {

    private String token;

    TokenPrincipal(String token) {
        if (token == null || token.trim().isEmpty()) {
            log.warn("Token is blank");
            throw new IllegalArgumentException("token is blank");
        }
        this.token = token;
    }

    @Override
    public String getName() {
        return token;
    }
}
