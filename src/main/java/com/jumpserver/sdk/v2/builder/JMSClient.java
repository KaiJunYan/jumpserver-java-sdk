package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.jumpserver.assets.AssertsService;
import com.jumpserver.sdk.v2.jumpserver.luna.LunaService;
import com.jumpserver.sdk.v2.jumpserver.org.OrgService;
import com.jumpserver.sdk.v2.jumpserver.permissions.PermissionService;
import com.jumpserver.sdk.v2.jumpserver.users.UserService;

public interface JMSClient {

    //token 信息
    Token getToken();

    boolean hasXpack();

    //users 用户信息
    UserService users();

    //assets 资产信息
    AssertsService assets();

    //assets 资产信息
    OrgService orgs();

    //luna 终端界面
    LunaService luna();

    //permissions 授权规则
    PermissionService permissions();

}
