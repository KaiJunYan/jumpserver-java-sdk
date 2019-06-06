package com.jumpserver.sdk.v2.httpclient;

import com.google.common.base.Function;

import static com.jumpserver.sdk.v2.common.ClientConstants.URI_SEP;

public class EndpointURIFromRequestFunction implements Function<HttpRequest<?>, String> {


    @Override
    public String apply(HttpRequest<?> request) {
        if (request.getEndpoint().endsWith(URI_SEP) || request.getPath().startsWith(URI_SEP))
            return escape(request.getEndpoint() + request.getPath());

        return escape(request.getEndpoint() + URI_SEP + request.getPath());
    }

    private String escape(String uri) {
        return uri.replaceAll(" ", "%20");
    }

}
