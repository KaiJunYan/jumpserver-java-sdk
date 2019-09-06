package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.api.Apis;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.httpclient.build.Config;
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
    private Config config;
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

    public static JMSClient createSession(Token token, Map<String, Object> headers, Config config) {
        return new JMSClientImpl(token, headers, config);
    }

    private JMSClientImpl(Token token, Map<String, Object> headers, Config config) {
        this.headers = headers;
        this.token = token;
        this.config = config;
        map.put("client", this);
    }

    @Override
    public Token getToken() {
        return token;
    }

    public Config getConfig() {
        if (config == null) {
            return Config.newConfig();
        }
        return this.config;
    }

    public Map getHeaders() {
        return this.headers;
    }

}


