package com.jumpserver.sdk.v2.jumpserver.luna;

import com.alibaba.fastjson.JSON;
import com.jumpserver.sdk.v2.builder.JMSClientImpl;
import com.jumpserver.sdk.v2.common.BaseJMSService;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.exceptions.JmsException;
import com.jumpserver.sdk.v2.model.Luna;


/**
 * @author yankaijun
 * @date 2018/10/16 上午10:34
 */
public class LunaServiceImpl extends BaseJMSService implements LunaService {

    @Override
    public Luna getLunaToken(Luna luna) {
        return post(Luna.class, ClientConstants.LUNA_TOKEN).json(JSON.toJSONString(luna)).execute();
    }

    @Override
    public String connectLunaLinux(String token) {
        JMSClientImpl current = JMSClientImpl.getCurrent();
        if (current == null) {
            throw new JmsException("Unable to retrieve current session. Please verify thread has a current session available.");
        }
        StringBuffer result = new StringBuffer();
        result.append(current.getToken().getEndpoint())
                .append(ClientConstants.LUNA_LINUX_CONNECT)
                .append(token);
        return result.toString();
    }

    @Override
    public String connectLunaWindows(String token) {
        JMSClientImpl current = JMSClientImpl.getCurrent();
        if (current == null) {
            throw new JmsException("Unable to retrieve current session. Please verify thread has a current session available.");
        }
        StringBuffer result = new StringBuffer();
        result.append(current.getToken().getEndpoint())
                .append(ClientConstants.LUNA_WINDOWS_CONNECT)
                .append(token);
        return result.toString();
    }

    @Override
    public String connectLunaUrl(String assetId) {
        JMSClientImpl current = JMSClientImpl.getCurrent();
        if (current == null) {
            throw new JmsException("Unable to retrieve current session. Please verify thread has a current session available.");
        }
        StringBuffer result = new StringBuffer();
        result.append(current.getToken().getEndpoint())
                .append(ClientConstants.LUNA_URL)
                .append(assetId);
        return result.toString();
    }
}
