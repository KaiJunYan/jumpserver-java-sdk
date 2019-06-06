package com.jumpserver.sdk.v2.httpclient;

import com.jumpserver.sdk.v2.builder.JMSClientImpl;
import com.jumpserver.sdk.v2.builder.OSAuthenticator;
import com.jumpserver.sdk.v2.common.ClientConstants;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpExecutorService {

    private static final Logger LOG = LoggerFactory.getLogger(HttpExecutorService.class);

    public <R> HttpResponse execute(HttpRequest<R> request) {
        try {
            return invoke(request);
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    private <R> HttpResponse invoke(HttpRequest<R> request) throws Exception {

        HttpCommand<R> command = HttpCommand.create(request);
        try {
            return invokeRequest(command);
        } catch (Exception re) {
            throw re;
        }
    }

    private <R> HttpResponse invokeRequest(HttpCommand<R> command) throws Exception {
        CloseableHttpResponse response = command.execute();

        if (command.getRetries() == 0 && (response.getStatusLine().getStatusCode() == 401 || response.getStatusLine().getStatusCode() == 403)
                && !command.getRequest().getHeaders().containsKey(ClientConstants.HEADER_FOR_AUTH)) {
            try {
                OSAuthenticator.reAuthenticate();
                command.getRequest().getHeaders().put(ClientConstants.HEADER_AUTHORIZATION, JMSClientImpl.getCurrent().getToken().getToken());
            } finally {
                response.close();
            }
            return invokeRequest(command.incrementRetriesAndReturn());
        }
        return HttpResponseImpl.wrap(response);
    }

}
