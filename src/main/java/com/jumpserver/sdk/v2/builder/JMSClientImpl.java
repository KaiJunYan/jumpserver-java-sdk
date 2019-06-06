package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.api.Apis;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.httpclient.Config;
import com.jumpserver.sdk.v2.jumpserver.assets.AssertsService;
import com.jumpserver.sdk.v2.jumpserver.luna.LunaService;
import com.jumpserver.sdk.v2.jumpserver.org.OrgService;
import com.jumpserver.sdk.v2.jumpserver.permissions.PermissionService;
import com.jumpserver.sdk.v2.jumpserver.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yankaijun
 * @date 2018/10/16 上午9:16
 */
public class JMSClientImpl implements JMSClient {

    private Token token;
    private Map<String, Object> headers;
    private static final Logger LOG = LoggerFactory.getLogger(JMSClientImpl.class);
    @SuppressWarnings("rawtypes")
    private static final Map<String, JMSClientImpl> map = new HashMap<>();

    public static JMSClientImpl getCurrent() {
        return map.get("client");
    }

    @Override
    public UserService users() {
        return Apis.getUserServices();
    }

    @Override
    public AssertsService assets() {
        return Apis.getAssetsServices();
    }

    @Override
    public OrgService orgs() {
        return Apis.getOrgServices();
    }

    @Override
    public LunaService luna() {
        return Apis.getLunaServices();
    }

    @Override
    public PermissionService permissions() {
        return Apis.getPermissionServices();
    }

    public static JMSClient createSession(Token token, Map<String, Object> headers) {
        return new JMSClientImpl(token, headers);
    }

    @Override
    public boolean hasXpack() {
        if (headers.get(ClientConstants.X_JMS_ORG) != null) {
            try {
                this.orgs().listOrg();
                this.token.setXpack(true);
                return true;
            } catch (Exception e) {
                LOG.error("访问xpack插件失败，不存在xpack插件");
            }
        }
        return false;
    }

    private JMSClientImpl(Token token, Map<String, Object> headers) {
        this.headers = headers;
        this.token = token;
        map.put("client", this);
    }

    public Token getToken() {
        return token;
    }

    public Config getConfig() {
        return Config.newConfig();
    }

    public Map getHeaders() {
        return this.headers;
    }

}


