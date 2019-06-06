package com.jumpserver.sdk.v2.api;

public interface APIProvider {

    void initialize();

    <T> T get(Class<T> api);
}
