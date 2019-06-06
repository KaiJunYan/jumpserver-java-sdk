package com.jumpserver.sdk.v2.httpclient;

import com.alibaba.fastjson.JSON;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.exceptions.ClientResponseException;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class HttpResponseImpl implements HttpResponse {

    private static final Logger LOG = LoggerFactory.getLogger(HttpResponseImpl.class);
    private CloseableHttpResponse response;

    private HttpResponseImpl(CloseableHttpResponse response) {
        this.response = response;
    }

    public static HttpResponseImpl wrap(CloseableHttpResponse response) {
        return new HttpResponseImpl(response);
    }

    public CloseableHttpResponse unwrap() {
        return response;
    }

    public <T> T getEntity(Class<T> returnType) {
        return HttpEntityHandler.handle(this, returnType, false);
    }

    public <T> List<T> getEntityList(Class<T> returnType) {
        return HttpEntityHandler.handleList(this, returnType, true);
    }

    public int getStatus() {
        return response.getStatusLine().getStatusCode();
    }

    @Override
    public String getStatusMessage() {
        return response.getStatusLine().getReasonPhrase();
    }

    public InputStream getInputStream() {
        HttpEntity entity = response.getEntity();
        try {
            if (entity != null)
                return entity.getContent();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public String header(String name) {
        Header header = response.getFirstHeader(name);
        return (header != null) ? header.getValue() : null;
    }

    public Map<String, String> headers() {
        Map<String, String> retHeaders = new HashMap<String, String>();
        Header[] headers = response.getAllHeaders();

        for (Header h : headers) {
            retHeaders.put(h.getName(), h.getValue());
        }
        return retHeaders;
    }

    @Override
    public <T> T readEntity(Class<T> typeToReadAs) {
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return null;
        }
        try {
            checkNotNull(entity.getContent(), "Entity content is null.");
            return JSON.parseObject(EntityUtils.toString(entity, "UTF-8"), typeToReadAs);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ClientResponseException(e.getMessage());
        }
    }

    @Override
    public <T> List<T> readEntityList(Class<T> typeToReadAs) {
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return null;
        }
        try {
            checkNotNull(entity.getContent(), "Entity content is null.");
            return JSON.parseArray(EntityUtils.toString(entity, "UTF-8"), typeToReadAs);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ClientResponseException(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        if (response != null)
            response.close();
    }

    @Override
    public String getContentType() {
        return header(ClientConstants.HEADER_CONTENT_TYPE);
    }
}
