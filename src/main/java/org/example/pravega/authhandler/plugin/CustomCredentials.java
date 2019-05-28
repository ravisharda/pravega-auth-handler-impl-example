package org.example.pravega.authhandler.plugin;

import io.pravega.client.stream.impl.Credentials;

public class CustomCredentials implements Credentials {

    private static final String TOKEN = "static-token";

    @Override
    public String getAuthenticationType() {
        return "CustomMethod";
    }

    @Override
    public String getAuthenticationToken() {
        return TOKEN;
    }
}
