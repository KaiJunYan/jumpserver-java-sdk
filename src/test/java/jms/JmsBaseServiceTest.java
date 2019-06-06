package jms;

import com.alibaba.fastjson.JSONObject;
import com.jumpserver.sdk.model.Asset;
import com.jumpserver.sdk.model.Luna;
import com.jumpserver.sdk.service.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class JmsBaseServiceTest {

    private JmsBaseService jmsBaseService;

    @Before
    public void getJmsAssetsService() {
//        jmsBaseService = new JmsBaseService("http://localhost:8088", "admin", "admin");
//        jmsBaseService = new JmsBaseService("http://localhost:8088", "b79cfee265082c9f1cc0cec269de8c28c8ee5744");
        System.out.println(jmsBaseService.TOKEN);

    }

    @Test
    public void testGetTokenRetry() {
        JmsLunaService jmsLunaService = jmsBaseService.jmsLunaService();
        jmsLunaService.getToken();
        for (int i = 0; i < 100; i++) {
            System.out.println("当前执行 ::::  "+i);
            Luna luna = new Luna();
            luna.setAsset("fe5fe097-30d6-48e9-b2c3-b3e7f2147bf5");
            luna.setUser("a8cf9423-f99a-4bab-8dd5-74b3f4e7bd43");
            luna.setSystem_user("d7e5a50b-889a-4870-9ae2-3a0adad326d2");
            Map<String, String> lunaToken = jmsLunaService.getLunaToken(luna);
            String token = JSONObject.parseObject(lunaToken.get("resultStr")).getString("token");
            Map<String, String> map = jmsLunaService.connectLunaLinux(token);
            String str = map.get("resultStr");
            System.out.println(str);
        }
    }


    @Test
    public void t11() {
        JmsAssetsService jmsAssetsService = jmsBaseService.jmsAssetsService();
        Asset asset = new Asset();
        asset.setHostname("SDK-hostName-modify");
        asset.setIp("192.168.12.12");
        Map<String, String> map = jmsAssetsService.addAsset(asset);
        System.out.println(map);
    }

}
