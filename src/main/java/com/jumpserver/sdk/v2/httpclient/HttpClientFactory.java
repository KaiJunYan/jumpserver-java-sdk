package com.jumpserver.sdk.v2.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientFactory {

    public static final HttpClientFactory INSTANCE = new HttpClientFactory();

    private CloseableHttpClient client;

    CloseableHttpClient getClient(Config config) {
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
        /*if (config.getProxy() != null) {
            try {
                URL url = new URL(config.getProxy().getHost());
                HttpHost proxy = new HttpHost(url.getHost(), config.getProxy().getPort(), url.getProtocol());
                cb.setProxy(proxy);
            } catch (MalformedURLException e) {
                LOG.error(e.getMessage(), e);
            }
        }*/

        if (config.isIgnoreSSLVerification()) {
            cb.setSslcontext(UntrustedSSL.getSSLContext());
            cb.setHostnameVerifier(new AllowAllHostnameVerifier());
        }

        if (config.getSslContext() != null)
            cb.setSslcontext(config.getSslContext());

        if (config.getMaxConnections() > 0) {
            cb.setMaxConnTotal(config.getMaxConnections());
        }

        if (config.getMaxConnectionsPerRoute() > 0) {
            cb.setMaxConnPerRoute(config.getMaxConnectionsPerRoute());
        }

        RequestConfig.Builder rcb = RequestConfig.custom();

        if (config.getConnectTimeout() > 0)
            rcb.setConnectTimeout(config.getConnectTimeout());

        if (config.getReadTimeout() > 0)
            rcb.setSocketTimeout(config.getReadTimeout());

        if (config.getReadTimeout() > 0)
            rcb.setSocketTimeout(config.getReadTimeout());

        return cb.setDefaultRequestConfig(rcb.build()).build();
    }

}
