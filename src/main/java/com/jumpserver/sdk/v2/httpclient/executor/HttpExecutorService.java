package com.jumpserver.sdk.v2.httpclient.executor;

import com.jumpserver.sdk.v2.httpclient.request.HttpCommand;
import com.jumpserver.sdk.v2.httpclient.request.HttpRequest;
import com.jumpserver.sdk.v2.httpclient.response.HttpResponse;
import com.jumpserver.sdk.v2.httpclient.response.HttpResponseImpl;
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
        return HttpResponseImpl.wrap(response);
    }

}
