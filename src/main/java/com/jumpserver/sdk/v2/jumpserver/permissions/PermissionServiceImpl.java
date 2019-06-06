package com.jumpserver.sdk.v2.jumpserver.permissions;

import com.alibaba.fastjson.JSON;
import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.common.BaseJMSService;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.model.AssetsPermission;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author yankaijun
 * @date 2018/10/16 上午10:34
 */
public class PermissionServiceImpl extends BaseJMSService implements PermissionService {
    @Override
    public List<AssetsPermission> list() {
        return get(AssetsPermission.class, uri(ClientConstants.ASSET_PERMISSIONS)).executeList();
    }

    @Override
    public AssetsPermission getAssetsPermission(String permissionId) {
        checkNotNull(permissionId);
        return get(AssetsPermission.class, ClientConstants.ASSET_PERMISSIONS, permissionId, "/").execute();
    }

    @Override
    public AssetsPermission updateAssetsPermission(AssetsPermission assetspermission) {
        checkNotNull(assetspermission);
        return patch(AssetsPermission.class, ClientConstants.ASSET_PERMISSIONS, assetspermission.getId(), "/").json(JSON.toJSONString(assetspermission)).execute();
    }

    @Override
    public AssetsPermission createAssetsPermission(AssetsPermission assetspermission) {
        checkNotNull(assetspermission);
        return post(AssetsPermission.class, uri(ClientConstants.ASSET_PERMISSIONS))
                .json(JSON.toJSONString(assetspermission))
                .execute();
    }

    @Override
    public ActionResponse deleteAssetsPermission(String permissionId) {
        checkNotNull(permissionId);
        return deleteWithResponse(ClientConstants.ASSET_PERMISSIONS, permissionId, "/").execute();
    }
}
