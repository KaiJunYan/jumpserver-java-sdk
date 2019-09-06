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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/***
 *  认证器
 */
public class OSAuthenticator {

    private static final String TOKEN_INDICATOR = "Tokens";
    private static final Logger LOG = LoggerFactory.getLogger(OSAuthenticator.class);

    @SuppressWarnings("rawtypes")
    public static JMSClient invoke(String endpoint, String name, String password, Map<String, Object> headersToSession, Config config) {
        return authenticate(endpoint, name, password, headersToSession, config);
    }

    private static JMSClient authenticate(String endpoint, String username, String password, Map<String, Object> headers, Config config) throws AuthenticationException {
        JSONObject jsonObject = new JSONObject() {{
            put("username", username);
            put("password", password);
        }};
        if (endpoint.endsWith(ClientConstants.URI_SEP)) {
            endpoint = endpoint.substring(0, endpoint.length() - 1);
        }
        //获得token的请求
        HttpRequest<Token> request = HttpRequest.builder(Token.class)
                .endpoint(endpoint)
                .method(HttpMethod.POST)
                .header(ClientConstants.HEADER_FOR_AUTH, TOKEN_INDICATOR)
                .json(jsonObject.toString())
                .config(config)
                .path(ClientConstants.TOKEN)
                .build();

        JMSClient client;
        HttpResponse response = null;
        try {
            response = HttpExecutor.create().execute(request);
            if (response.getStatus() >= HttpStatus.SC_BAD_REQUEST) {
                String s = IOUtils.toString(response.getInputStream(), "UTF-8");
                LOG.error("authenticate error，code: {} ，message: {}", response.getStatus(), s);
                throw new AuthenticationException(s);
            }
            Token token = response.getEntity(Token.class);
            token.setUsername(username);
            token.setPassword(password);
            token.setEndpoint(endpoint);
            client = JMSClientImpl.createSession(token, headers, config);
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
