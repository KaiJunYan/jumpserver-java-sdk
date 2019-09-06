package com.jumpserver.sdk.v2.httpclient.build;

/**
 * @author yankaijun
 */
public class Proxy {
    private String host;
    private int port;

    public Proxy withHost(String host) {
        this.host = host;
        return this;
    }

    public Proxy withPort(int port) {
        this.port = port;
        return this;
    }

    public static Proxy newProxy() {
        return new Proxy();
    }

    public String getHost() {
        return host;
    }

    public Proxy setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public Proxy setPort(int port) {
        this.port = port;
        return this;
    }
}
