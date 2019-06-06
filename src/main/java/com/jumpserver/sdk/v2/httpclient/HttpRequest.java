package com.jumpserver.sdk.v2.httpclient;

import com.google.common.collect.Maps;
import com.jumpserver.sdk.v2.builder.JMSClientImpl;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.common.HttpMethod;
import com.jumpserver.sdk.v2.exceptions.JmsException;
import com.jumpserver.sdk.v2.model.entity.ModelEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpRequest<R> {

    String endpoint;
    String path;
    Class<R> returnType;
    Object entity;
    String contentType = ClientConstants.CONTENT_TYPE_JSON;
    HttpMethod method = HttpMethod.GET;
    String json;
    private Config config;
    private Map<String, List<Object>> queryParams;
    private Map<String, Object> headers = new HashMap<String, Object>();

    public HttpRequest() {
    }


    public static RequestBuilder<Void> builder() {
        return new RequestBuilder<Void>(Void.class);
    }

    public static <R> RequestBuilder<R> builder(Class<R> returnType) {
        return new RequestBuilder<R>(returnType);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getContentType() {
        return contentType;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getPath() {
        return path;
    }

    public String getJson() {
        return (json == null) ? "" : json;
    }

    public boolean hasJson() {
        return (json != null);
    }

    public Class<R> getReturnType() {
        return returnType;
    }

    public Object getEntity() {
        return entity;
    }

    public boolean hasQueryParams() {
        return queryParams != null && !queryParams.isEmpty();
    }

    public Map<String, List<Object>> getQueryParams() {
        return queryParams;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public boolean hasHeaders() {
        return !headers.isEmpty();
    }

    public Config getConfig() {
        return config != null ? config : Config.DEFAULT;
    }

    public static final class RequestBuilder<R> {
        HttpRequest<R> request;

        public RequestBuilder(HttpRequest<R> request) {
            this.request = request;
        }

        public RequestBuilder(Class<R> returnType) {
            request = new HttpRequest<R>();
            request.returnType = returnType;
        }

        /**
         * @see HttpRequest#getEndpoint()
         */
        public RequestBuilder<R> endpoint(String endpoint) {
            request.endpoint = endpoint;
            return this;
        }

        /**
         * @see HttpRequest#getPath()
         */
        public RequestBuilder<R> path(String path) {
            request.path = path;
            return this;
        }

        /**
         * @see HttpRequest#getMethod()
         */
        public RequestBuilder<R> method(HttpMethod method) {
            request.method = method;
            return this;
        }

        /**
         * @see HttpRequest#getEntity()
         */
        public RequestBuilder<R> entity(ModelEntity entity) {
            request.entity = entity;
            return this;
        }

        /**
         * Sets a client configuration to use with this session
         */
        public RequestBuilder<R> config(Config config) {
            request.config = config;
            return this;
        }

        /**
         * Pushes the Map of Headers into the existing headers for this request
         *
         * @param headers the headers to append
         * @return the request builder
         */
        public RequestBuilder<R> headers(Map<String, ? extends Object> headers) {
            request.getHeaders().putAll(headers);
            return this;
        }

        /**
         * Adds a new Header to the request
         *
         * @param name  the header name
         * @param value the header value
         * @return the request builder
         */
        public RequestBuilder<R> header(String name, Object value) {
            request.getHeaders().put(name, value);
            return this;
        }

        /**
         * Adds a Key/Value based Query Param
         *
         * @param key   the key
         * @param value the value
         * @return the request builder
         */
        public RequestBuilder<R> queryParam(String key, Object value) {
            if (value == null)
                return this;

            if (request.queryParams == null)
                request.queryParams = Maps.newHashMap();

            if (request.queryParams.containsKey(key)) {
                List<Object> values = request.queryParams.get(key);
                values.add(value);
            } else {
                List<Object> values = new ArrayList<Object>();
                values.add(value);
                request.queryParams.put(key, values);
            }
            return this;
        }

        /**
         * Updates a Key/Value based Query Param
         *
         * @param key   the key
         * @param value the value
         * @return the request builder
         */
        public RequestBuilder<R> updateQueryParam(String key, Object value) {
            if (value == null)
                return this;

            if (request.queryParams == null)
                request.queryParams = Maps.newHashMap();

            List<Object> values = new ArrayList<Object>();
            values.add(value);
            request.queryParams.put(key, values);

            return this;
        }

        /**
         * AdHoc JSON object to Post/Put
         *
         * @param json the JSON object in String form
         * @return the request builder
         */
        public RequestBuilder<R> json(String json) {
            request.json = json;
            return this;
        }

        /**
         * Overrides the default content type for the request
         *
         * @param contentType the content type to use in the request
         * @return the request builder
         */
        public RequestBuilder<R> contentType(String contentType) {
            if (contentType != null)
                request.contentType = contentType;
            return this;
        }

        /**
         * Builds the HttpRequest
         *
         * @return HttpRequest
         */
        public HttpRequest<R> build() {
            if (!request.headers.containsKey(ClientConstants.HEADER_FOR_AUTH)) {
                JMSClientImpl ses = JMSClientImpl.getCurrent();
                if (ses == null) {
                    throw new JmsException("Unable to retrieve current when building a  HttpRequest ");
                }
                request.getHeaders().put(ClientConstants.HEADER_AUTHORIZATION, ClientConstants.BEARER + ses.getToken().getToken());
                request.endpoint = ses.getToken().getEndpoint();
            }
            return request;
        }
    }
}
