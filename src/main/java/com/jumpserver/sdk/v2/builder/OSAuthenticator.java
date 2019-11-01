package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.exceptions.AuthenticationException;
import com.jumpserver.sdk.v2.httpclient.build.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/***
 *  认证器
 */
public class OSAuthenticator {

    private static final Logger LOG = LoggerFactory.getLogger(OSAuthenticator.class);

    @SuppressWarnings("rawtypes")
    public static JMSClient invoke(String endpoint, String keyId, String keySecret, Map<String, Object> headersToSession, Config config) {
        return authenticate(endpoint, keyId, keySecret, headersToSession, config);
    }

    private static JMSClient authenticate(String endpoint, String keyId, String keySecret, Map<String, Object> headers, Config config) throws AuthenticationException {
        if (endpoint.endsWith(ClientConstants.URI_SEP)) {
            endpoint = endpoint.substring(0, endpoint.length() - 1);
        }
        JMSClient client;
        try {
            ApiKey apiKey = new ApiKey(keyId, keySecret, endpoint);
            client = JMSClientImpl.createSession(apiKey, headers, config);
            //execute a request
            client.users().list();
        } catch (Exception e) {
            LOG.error("OSAuthenticator验证异常：{}", e.getMessage());
            throw new AuthenticationException(e.getMessage());
        }
        return client;
    }

}
