package com.jumpserver.sdk.v2.builder;

import com.alibaba.fastjson.JSONObject;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.common.HttpMethod;
import com.jumpserver.sdk.v2.exceptions.AuthenticationException;
import com.jumpserver.sdk.v2.httpclient.build.Config;
import com.jumpserver.sdk.v2.httpclient.response.HttpEntityHandler;
import com.jumpserver.sdk.v2.httpclient.executor.HttpExecutor;
import com.jumpserver.sdk.v2.httpclient.request.HttpRequest;
import com.jumpserver.sdk.v2.httpclient.response.HttpResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/***
 *  认证器
 */
public class OSAuthenticator {

    private static final Logger LOG = LoggerFactory.getLogger(OSAuthenticator.class);

    @SuppressWarnings("rawtypes")
    public static JMSClient invoke(String endpoint, String user, String keyId, String keySecret, Map<String, Object> headersToSession, Config config) {
        return authenticate(endpoint, user, keyId, keySecret, headersToSession, config);
    }

    private static JMSClient authenticate(String endpoint, String user, String keyId, String keySecret, Map<String, Object> headers, Config config) throws AuthenticationException {
        JSONObject jsonObject = new JSONObject() {{
            put("username", user);
            put("keyId", keyId);
            put("keySecret", keySecret);
        }};
        if (endpoint.endsWith(ClientConstants.URI_SEP)) {
            endpoint = endpoint.substring(0, endpoint.length() - 1);
        }

        JMSClient client;
        HttpResponse response = null;
        try {
            Token token = new Token();
            token.setUsername(user);
            token.setEndpoint(endpoint);

            ApiKey apiKey = new ApiKey();
            apiKey.setKeySecret(keySecret);
            apiKey.setKeyId(keyId);
            apiKey.setEndpoint(endpoint);

            client = JMSClientImpl.createSession(token, apiKey, headers, config);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        } finally {
            if (response != null) {
                HttpEntityHandler.closeQuietly(response);
            }
        }
        return client;
    }

}
