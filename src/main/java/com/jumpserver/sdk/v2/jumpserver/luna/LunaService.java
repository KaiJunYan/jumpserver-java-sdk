package com.jumpserver.sdk.v2.jumpserver.luna;

import com.jumpserver.sdk.v2.model.Luna;

/**
 * @author yankaijun
 * @date 2018/10/16 上午10:34
 */
public interface LunaService {

    Luna getLunaToken(Luna luna);

    String connectLunaLinux(String token);

    String connectLunaWindows(String token);

    String connectLunaUrl(String assetId);
}
