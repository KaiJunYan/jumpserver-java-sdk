package com.jumpserver.sdk.v2.httpclient.build;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class HttpClientFactory {

    public static final HttpClientFactory INSTANCE = new HttpClientFactory();
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientFactory.class);

    private CloseableHttpClient client;

    public CloseableHttpClient getClient(Config config) {
        if (client == null) {
            synchronized (this) {
                if (client == null) {
                    client = buildClient(config);
                }
            }
        }
        return client;
    }

    private CloseableHttpClient buildClient(Config config) {
        HttpClientBuilder cb = HttpClientBuilder.create();
        //agent
        //HttpClientBuilder cb = HttpClientBuilder.create().setUserAgent(USER_AGENT);

        //代理
        if (config.getProxy() != null) {
            try {
                URL url = new URL(config.getProxy().getHost());
                HttpHost proxy = new HttpHost(url.getHost(), config.getProxy().getPort(), url.getProtocol());
                cb.setProxy(proxy);
            } catch (MalformedURLException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        if (config.isIgnoreSSLVerification()) {
            cb.setSSLContext(UntrustedSSL.getSSLContext());
            cb.setSSLHostnameVerifier(new NoopHostnameVerifier());
        }

        if (config.getSslContext() != null) {
            cb.setSSLContext(config.getSslContext());
        }

        if (config.getMaxConnections() > 0) {
            cb.setMaxConnTotal(config.getMaxConnections());
        }

        if (config.getMaxConnectionsPerRoute() > 0) {
            cb.setMaxConnPerRoute(config.getMaxConnectionsPerRoute());
        }

        RequestConfig.Builder rcb = RequestConfig.custom();

        if (config.getConnectTimeout() > 0) {
            rcb.setConnectTimeout(config.getConnectTimeout());
        }

        if (config.getReadTimeout() > 0) {
            rcb.setSocketTimeout(config.getReadTimeout());
        }

        return cb.setDefaultRequestConfig(rcb.build()).build();
    }

}
