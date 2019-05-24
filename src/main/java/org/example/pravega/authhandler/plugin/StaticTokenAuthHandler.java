package org.example.pravega.authhandler.plugin;

import io.pravega.auth.AuthException;
import io.pravega.auth.AuthHandler;
import io.pravega.auth.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@Slf4j
public class StaticTokenAuthHandler implements AuthHandler {

    private static final String TOKEN = "static-token";

    @Override
    public String getHandlerName() {
        return "StaticTokenAuthHandler";
    }

    @Override
    public Principal authenticate(String token) throws AuthException {
        return new TokenPrincipal(TOKEN);
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
