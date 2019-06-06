package com.jumpserver.sdk.v2.httpclient;

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
