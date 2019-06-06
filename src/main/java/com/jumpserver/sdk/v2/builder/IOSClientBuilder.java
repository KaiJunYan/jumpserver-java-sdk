package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.exceptions.AuthenticationException;
import com.jumpserver.sdk.v2.httpclient.Config;

public interface IOSClientBuilder<T, R> {

    T withConfig(Config config);

    T credentials(String userId, String password);

    T endpoint(String endpoint);

    T header(String key, String value);

    R authenticate() throws AuthenticationException;

}
