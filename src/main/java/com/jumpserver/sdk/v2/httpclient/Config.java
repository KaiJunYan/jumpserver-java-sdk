package com.jumpserver.sdk.v2.httpclient;

import javax.net.ssl.SSLContext;

public final class Config {

    public static final Config DEFAULT = new Config();

    private int connectTimeout;
    private int readTimeout;
    private SSLContext sslContext;
    private boolean ignoreSSLVerification;
    private int maxConnections;
    private int maxConnectionsPerRoute;
    private String natHostOrIP;

    private Config() {
    }

    public static Config newConfig() {
        return new Config();
    }

    public Config withConnectionTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public Config withReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public Config withSSLVerificationDisabled() {
        this.ignoreSSLVerification = Boolean.TRUE;
        return this;
    }

    public Config withMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
        return this;
    }

    public Config withMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public Config withSSLContext(SSLContext sslContext) {
        this.sslContext = sslContext;
        return this;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public SSLContext getSslContext() {
        return sslContext;
    }

    public boolean isIgnoreSSLVerification() {
        return ignoreSSLVerification;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public int getMaxConnectionsPerRoute() {
        return maxConnectionsPerRoute;
    }

}
