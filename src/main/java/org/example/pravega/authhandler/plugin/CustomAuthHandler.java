package org.example.pravega.authhandler.plugin;

import io.pravega.auth.AuthException;
import io.pravega.auth.AuthHandler;
import io.pravega.auth.AuthenticationException;
import io.pravega.auth.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@Slf4j
public class CustomAuthHandler implements AuthHandler {

    private static final String TOKEN = "static-token";

    @Override
    public String getHandlerName() {
        return "CustomMethod";
    }

    @Override
    public Principal authenticate(String token) throws AuthException {
        if (token.equals(TOKEN)) {
            return new CustomPrincipal(TOKEN);
        } else {
            throw new AuthenticationException("Specified token was invalid");
        }
    }

    @Override
    public Permissions authorize(String resource, Principal principal) {
        log.debug("resource: {}, principal: {}", resource, principal);
        if (principal.getName().equals(TOKEN)) {
            return Permissions.READ_UPDATE;
        } else {
            return Permissions.NONE;
        }
    }

    @Override
    public void initialize(ServerConfig serverConfig) {
        log.debug("serverConfig: {}", serverConfig);
    }
}
