package com.jumpserver.sdk.v2.jumpserver.luna;

import com.jumpserver.sdk.v2.builder.JMSClientImpl;
import com.jumpserver.sdk.v2.common.BaseJmsService;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.exceptions.JmsException;

/**
 * @author yankaijun
 * @date 2018/10/16 上午10:34
 */
public class LunaServiceImpl extends BaseJmsService implements LunaService {

    @Override
    public String connectLunaUrl(String assetId) {
        JMSClientImpl current = JMSClientImpl.getCurrent();
        if (current == null) {
            throw new JmsException("Unable to retrieve current session. Please verify thread has a current session available.");
        }
        StringBuffer result = new StringBuffer();
        result.append(current.getApiKey().getEndpoint())
                .append(ClientConstants.LUNA_URL)
                .append(assetId);
        return result.toString();
    }
}
