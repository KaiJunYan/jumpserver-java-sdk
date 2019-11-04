package com.jumpserver.sdk.v2.builder;

public class ApiKey {
    private String keyId;
    private String keySecret;
    private String endpoint;

    public ApiKey(String keyId, String keySecret, String endpoint) {
        this.keyId = keyId;
        this.keySecret = keySecret;
        this.endpoint = endpoint;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public String toString() {
        return "ApiKey{" +
                "keyId='" + keyId + '\'' +
                ", keySecret='" + keySecret + '\'' +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
