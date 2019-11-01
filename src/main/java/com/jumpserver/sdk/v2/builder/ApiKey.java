package com.jumpserver.sdk.v2.builder;

public class ApiKey {
    private String keyId;
    private String keySecret;
    private String endpoint;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
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
