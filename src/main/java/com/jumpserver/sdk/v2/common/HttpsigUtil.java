package com.jumpserver.sdk.v2.common;

import com.google.common.net.MediaType;
import com.jumpserver.sdk.v2.builder.ApiKey;
import net.adamcin.httpsig.api.*;
import net.adamcin.httpsig.hmac.HmacKey;

import java.util.HashMap;
import java.util.Map;

public class HttpsigUtil {

    // 获取签名后的头部
    public static Map<String, String> getSignHeaders(ApiKey apiKey, String method, String path) {
        DefaultKeychain provider = new DefaultKeychain();
        HmacKey hmacKey = new HmacKey(apiKey.getKeyId(), apiKey.getKeySecret());
        provider.add(hmacKey);

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", MediaType.JSON_UTF_8.toString());
        headers.put("keyId", apiKey.getKeyId());
        headers.put("secret", apiKey.getKeySecret());
        headers.put("algorithm", Algorithm.HMAC_SHA256.name());
        RequestContent.Builder requestContentBuilder = new RequestContent.Builder();

        requestContentBuilder.setRequestTarget(method, path);
        for (Map.Entry<String, String> header : headers.entrySet()) {
            requestContentBuilder.addHeader(header.getKey(), header.getValue());
        }
        if (requestContentBuilder.build().getDate() == null) {
            requestContentBuilder.addDateNow();
            String dateValue = requestContentBuilder.build().getDate();
            requestContentBuilder.addHeader("date", dateValue);
            headers.put("date", dateValue);
        }
        Signer signer = new Signer(provider, key -> hmacKey.getId());
        RequestContent requestContent = requestContentBuilder.build();
        Authorization authorization = signer.sign(requestContent);
        if (authorization != null) {
            headers.put(ClientConstants.HEADER_AUTHORIZATION, authorization.getHeaderValue());
        }
        return headers;
    }

}
