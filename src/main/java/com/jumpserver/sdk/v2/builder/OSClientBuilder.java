package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.httpclient.Config;

import java.util.HashMap;
import java.util.Map;

public abstract class OSClientBuilder<T, R> implements IOSClientBuilder<T, R> {

    String endpoint;
    String user;
    Config config;
    String password;
    Map<String, Object> headers = new HashMap<>();

    @Override
    public T withConfig(Config config) {
        this.config = config;
        return (T) this;
    }

}
