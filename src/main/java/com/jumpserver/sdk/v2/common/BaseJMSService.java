package com.jumpserver.sdk.v2.common;

import com.google.common.base.Joiner;
import com.jumpserver.sdk.v2.builder.JMSClientImpl;
import com.jumpserver.sdk.v2.exceptions.JmsException;
import com.jumpserver.sdk.v2.httpclient.HttpExecutor;
import com.jumpserver.sdk.v2.httpclient.HttpRequest;
import com.jumpserver.sdk.v2.httpclient.HttpResponse;
import com.jumpserver.sdk.v2.model.entity.ModelEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BaseJMSService {

    private static ThreadLocal<String> reqIdContainer = new ThreadLocal<String>();

    public String getXOpenstackRequestId() {
        return reqIdContainer.get();
    }

    protected BaseJMSService() {
    }

    protected <R> Invocation<R> get(Class<R> returnType, String... path) {
        return builder(returnType, path, HttpMethod.GET);
    }

    protected <R> Invocation<R> getList(Class<R> returnType, String... path) {
        return builder(returnType, path, HttpMethod.GET);
    }

    protected <R> Invocation<R> post(Class<R> returnType, String... path) {
        return builder(returnType, path, HttpMethod.POST);
    }

    protected <R> Invocation<ActionResponse> postWithResponse(String... path) {
        return builder(ActionResponse.class, path, HttpMethod.POST);
    }

    protected <R> Invocation<R> put(Class<R> returnType, String... path) {
        return builder(returnType, path, HttpMethod.PUT);
    }

    protected <R> Invocation<R> patch(Class<R> returnType, String... path) {
        return builder(returnType, path, HttpMethod.PATCH);
    }

    protected <R> Invocation<ActionResponse> patchWithResponse(String... path) {
        return builder(ActionResponse.class, path, HttpMethod.PATCH);
    }

    protected <R> Invocation<R> delete(Class<R> returnType, String... path) {
        return builder(returnType, path, HttpMethod.DELETE);
    }

    protected <R> Invocation<ActionResponse> deleteWithResponse(String... path) {
        return builder(ActionResponse.class, path, HttpMethod.DELETE);
    }

    protected <R> Invocation<R> head(Class<R> returnType, String... path) {
        return builder(returnType, path, HttpMethod.HEAD);
    }

    protected <R> Invocation<R> request(HttpMethod method, Class<R> returnType, String path) {
        return builder(returnType, path, method);
    }

    protected String uri(String path, Object... params) {
        if (params.length == 0)
            return path;
        return String.format(path, params);
    }

    protected String replace(String path, String id) {
        if (path.length() == 0 || id.length() == 0)
            return path;
        return path.replace("{id}", id);
    }

    private <R> Invocation<R> builder(Class<R> returnType, String[] path, HttpMethod method) {
        return builder(returnType, Joiner.on("").join(path), method);
    }

    @SuppressWarnings("rawtypes")
    private <R> Invocation<R> builder(Class<R> returnType, String path, HttpMethod method) {
        JMSClientImpl ses = JMSClientImpl.getCurrent();
        if (ses == null) {
            throw new JmsException("Unable to retrieve current session. Please verify thread has a current session available.");
        }
        HttpRequest.RequestBuilder<R> req = HttpRequest.builder(returnType).config(ses.getConfig())
                .method(method).path(path);
        Map headers = ses.getHeaders();
        if (headers != null && headers.size() > 0) {
            return new Invocation<R>(req).headers(headers);
        } else {
            return new Invocation<R>(req);
        }
    }

    protected static class Invocation<R> {
        HttpRequest.RequestBuilder<R> req;

        protected Invocation(HttpRequest.RequestBuilder<R> req) {
            this.req = req;
        }

        public HttpRequest<R> getRequest() {
            return req.build();
        }

        public Invocation<R> param(String name, Object value) {
            req.queryParam(name, value);
            return this;
        }

        public Invocation<R> updateParam(String name, Object value) {
            req.updateQueryParam(name, value);
            return this;
        }

        public Invocation<R> params(Map<String, ? extends Object> params) {
            if (params != null) {
                for (String name : params.keySet())
                    req.queryParam(name, params.get(name));
            }
            return this;
        }

        public Invocation<R> param(boolean condition, String name, Object value) {
            if (condition)
                req.queryParam(name, value);
            return this;
        }

        public Invocation<R> paramLists(Map<String, ? extends Iterable<? extends Object>> params) {
            if (params != null) {
                for (Map.Entry<String, ? extends Iterable<? extends Object>> pair : params.entrySet())
                    for (Object value : pair.getValue())
                        req.queryParam(pair.getKey(), value);
            }
            return this;
        }

        public Invocation<R> entity(ModelEntity entity) {
            req.entity(entity);
            return this;
        }

        public Invocation<R> contentType(String contentType) {
            req.contentType(contentType);
            return this;
        }

        public Invocation<R> json(String json) {
            req.json(json);
            return this;
        }

        public Invocation<R> headers(Map<String, ? extends Object> headers) {
            if (headers != null)
                req.headers(headers);
            return this;
        }

        public Invocation<R> header(String name, Object value) {
            req.header(name, value);
            return this;
        }

        public R execute() {
            HttpRequest<R> request = req.build();
            HttpResponse res = HttpExecutor.create().execute(request);
            return res.getEntity(request.getReturnType());
        }

        public List<R> executeList() {
            HttpRequest<R> request = req.build();
            HttpResponse res = HttpExecutor.create().execute(request);
            return res.getEntityList(request.getReturnType());
        }

    }

    protected <T> List<T> toList(T[] arr) {
        if (arr == null)
            return Collections.emptyList();
        return Arrays.asList(arr);
    }
}
