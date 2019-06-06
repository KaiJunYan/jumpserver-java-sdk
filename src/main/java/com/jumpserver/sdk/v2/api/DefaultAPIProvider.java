package com.jumpserver.sdk.v2.api;

import com.google.common.collect.Maps;
import com.jumpserver.sdk.v2.jumpserver.assets.AssertsService;
import com.jumpserver.sdk.v2.jumpserver.assets.AssertsServiceImpl;
import com.jumpserver.sdk.v2.jumpserver.luna.LunaService;
import com.jumpserver.sdk.v2.jumpserver.luna.LunaServiceImpl;
import com.jumpserver.sdk.v2.jumpserver.org.OrgService;
import com.jumpserver.sdk.v2.jumpserver.org.OrgServiceImpl;
import com.jumpserver.sdk.v2.jumpserver.permissions.PermissionService;
import com.jumpserver.sdk.v2.jumpserver.permissions.PermissionServiceImpl;
import com.jumpserver.sdk.v2.jumpserver.users.UserService;
import com.jumpserver.sdk.v2.jumpserver.users.UserServiceImpl;

import java.util.Map;

public class DefaultAPIProvider implements APIProvider {

    private static final Map<Class<?>, Class<?>> bindings = Maps.newHashMap();
    private static final Map<Class<?>, Object> instances = Maps.newConcurrentMap();

    @Override
    public void initialize() {
        bind(UserService.class, UserServiceImpl.class);
        bind(PermissionService.class, PermissionServiceImpl.class);
        bind(LunaService.class, LunaServiceImpl.class);
        bind(OrgService.class, OrgServiceImpl.class);
        bind(AssertsService.class, AssertsServiceImpl.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Class<T> api) {
        if (instances.containsKey(api))
            return (T) instances.get(api);

        if (bindings.containsKey(api)) {
            try {
                T impl = (T) bindings.get(api).newInstance();
                instances.put(api, impl);
                return impl;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void bind(Class<?> api, Class<?> impl) {
        bindings.put(api, impl);
    }
}
