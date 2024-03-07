package org.example.properties;

public final class GrpcClientProperties {
    private final String host = "localhost";
    private final int port = 6565;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
