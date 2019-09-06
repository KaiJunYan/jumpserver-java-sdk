package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.httpclient.build.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yankaijun
 * @date 2018/10/16 上午9:10
 */
public class ClientBuilder implements IOSClientBuilder<ClientBuilder, JMSClient> {

    String endpoint;
    String user;
    Config config;
    String password;
    Map<String, Object> headers = new HashMap<>();

    @Override
    public ClientBuilder withConfig(Config config) {
        this.config = config;
        return this;
    }

    @Override
    public ClientBuilder credentials(String user, String password) {
        this.user = user;
        this.password = password;
        return this;
    }

    @Override
    public ClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @Override
    public ClientBuilder header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    @Override
    public JMSClient authenticate() {
        return OSAuthenticator.invoke(endpoint, user, password, headers, config);
    }

}
