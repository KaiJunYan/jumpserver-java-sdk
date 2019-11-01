package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.exceptions.AuthenticationException;
import com.jumpserver.sdk.v2.httpclient.build.Config;

public interface IOSClientBuilder<T, R> {

    T withConfig(Config config);

    T credentials(String keyId, String keySecret);

    T endpoint(String endpoint);

    T header(String key, String value);

    R authenticate() throws AuthenticationException;

}
