package com.jumpserver.sdk.v2.httpclient.executor;

import com.jumpserver.sdk.v2.httpclient.request.HttpRequest;
import com.jumpserver.sdk.v2.httpclient.response.HttpResponse;

public class HttpExecutor {

    private static final HttpExecutor INSTANCE = new HttpExecutor();
    private HttpExecutorService service = new HttpExecutorService();

    private HttpExecutor() {
    }

    private HttpExecutorService service() {
        return  service;
    }

    public static HttpExecutor create() {
        return INSTANCE;
    }

    public <R> HttpResponse execute(HttpRequest<R> request) {
        return service().execute(request);
    }
}
