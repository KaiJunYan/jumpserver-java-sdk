package com.jumpserver.sdk.v2.builder;

/**
 * @author yankaijun
 * @date 2018/10/16 上午9:10
 */
public class ClientBuilder extends OSClientBuilder<ClientBuilder, JMSClient> implements IOSClientBuilder<ClientBuilder, JMSClient> {

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
        return OSAuthenticator.invoke(endpoint, user, password, headers);
    }

}
