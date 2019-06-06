package com.jumpserver.sdk.v2.httpclient;

import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.exceptions.ClientResponseException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/***
 * 对各类状态码进行处理
 * */
public class HttpEntityHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HttpEntityHandler.class);

    @SuppressWarnings("unchecked")
    public static <T> List<T> handleList(HttpResponse response, Class<T> returnType, boolean list) {
        if (response.getStatus() >= 400) {
            throw new ClientResponseException(response.getStatusMessage());
        }
        return response.readEntityList(returnType);
    }

    @SuppressWarnings("unchecked")
    public static <T> T handle(HttpResponse response, Class<T> returnType, boolean list) {
        try {
            String text = "";
            if (response.getStatus() >= 400) {
                try {
                    InputStream inputStream = response.getInputStream();
                    text = IOUtils.toString(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                throw new ClientResponseException(response.getStatusMessage() + " " + text);
            }
            if (returnType == Void.class) {
                return null;
            }

            if (returnType == ActionResponse.class) {
                return (T) ActionResponse.actionSuccess(response.getStatus());
            }
            return response.readEntity(returnType);
        } finally {
            closeQuietly(response);
        }
    }

    public static void closeQuietly(HttpResponse response) {
        try {
            response.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static int statusAndClose(HttpResponse response) {
        int status = response.getStatus();
        closeQuietly(response);
        return status;
    }
}
