package com.jumpserver.sdk.v2.httpclient.request;

import com.alibaba.fastjson.JSON;
import com.jumpserver.sdk.v2.httpclient.build.EndpointURIFunction;
import com.jumpserver.sdk.v2.httpclient.build.HttpClientFactory;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/***
 * request转化为command
 * command执行http请求
 */
public final class HttpCommand<R> {

    private static final Logger LOG = LoggerFactory.getLogger(HttpCommand.class);

    private HttpRequest<R> request;
    private CloseableHttpClient client;
    HttpUriRequest clientReq;
    private int retries;

    private HttpCommand(HttpRequest<R> request) {
        this.request = request;
    }

    public static <R> HttpCommand<R> create(HttpRequest<R> request) {
        HttpCommand<R> command = new HttpCommand<R>(request);
        command.initialize();
        return command;
    }

    private void initialize() {
        URI url;
        try {
            url = populateQueryParams(request);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
        client = HttpClientFactory.INSTANCE.getClient(request.getConfig());
        switch (request.getMethod()) {
            case POST:
                clientReq = new HttpPost(url);
                break;
            case PUT:
                clientReq = new HttpPut(url);
                break;
            case DELETE:
                clientReq = new HttpDelete(url);
                break;
            case HEAD:
                clientReq = new HttpHead(url);
                break;
            case PATCH:
                clientReq = new HttpPatch(url);
                break;
            case GET:
                clientReq = new HttpGet(url);
                break;
            default:
                throw new IllegalArgumentException("Unsupported http method: " + request.getMethod());
        }
        populateHeaders(request);
    }

    public CloseableHttpResponse execute() throws Exception {
        EntityBuilder builder = null;
        if (request.getEntity() != null) {
            if (InputStream.class.isAssignableFrom(request.getEntity().getClass())) {
                InputStreamEntity ise = new InputStreamEntity((InputStream) request.getEntity(),
                        ContentType.create(request.getContentType()));
                ((HttpEntityEnclosingRequestBase) clientReq).setEntity(ise);
            } else {
                builder = EntityBuilder.create().setContentType(ContentType.create(request.getContentType(), "UTF-8"))
                        .setText(JSON.toJSONString(request.getEntity()));
            }
        } else if (request.hasJson()) {
            LOG.info("请求json:" + request.getJson());
            builder = EntityBuilder.create().setContentType(ContentType.APPLICATION_JSON).setText(request.getJson());
        }

        if (builder != null && clientReq instanceof HttpEntityEnclosingRequestBase) {
            ((HttpEntityEnclosingRequestBase) clientReq).setEntity(builder.build());
        }

        /*Header[] allHeaders = clientReq.getAllHeaders();
        if (allHeaders.length > 0) {
            for (Header header : allHeaders) {
                if (ClientConstants.X_JMS_ORG.equals(header.getName()) || ClientConstants.HEADER_AUTHORIZATION.equals(header.getName())) {
                    LOG.info("请求Header：" + header.getName() + ":" + header.getValue());
                }
            }
        }*/
        return client.execute(clientReq);
    }

    /***
     * 构建请求的完整URL
     * @param request
     * @return
     * @throws URISyntaxException
     */
    private URI populateQueryParams(HttpRequest<R> request) throws URISyntaxException {
        URIBuilder uri = new URIBuilder(new EndpointURIFunction().apply(request));
        if (!request.hasQueryParams()) {
            return uri.build();
        }

        for (Map.Entry<String, List<Object>> entry : request.getQueryParams().entrySet()) {
            for (Object o : entry.getValue()) {
                uri.addParameter(entry.getKey(), String.valueOf(o));
            }
        }
        return uri.build();
    }

    /**
     * 构建请求Header
     *
     * @param request
     */
    private void populateHeaders(HttpRequest<R> request) {
        if (!request.hasHeaders()) {
            return;
        }
        for (Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            clientReq.addHeader(h.getKey(), String.valueOf(h.getValue()));
        }
    }

    public int getRetries() {
        return retries;
    }

    public HttpCommand<R> incrementRetriesAndReturn() {
        initialize();
        retries++;
        return this;
    }

    public HttpRequest<R> getRequest() {
        return request;
    }
}
