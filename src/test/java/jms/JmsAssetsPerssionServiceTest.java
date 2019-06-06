package jms;

import com.jumpserver.sdk.model.AssetsPermission;
import com.jumpserver.sdk.service.JmsPermissionService;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class JmsAssetsPerssionServiceTest {

    private JmsPermissionService jmsPermissionService;

    @Before
    public void getJmsAssetsService() {
        jmsPermissionService = new JmsPermissionService("http://localhost:8088", "admin", "admin");
        String token = jmsPermissionService.getToken();
        System.out.println(token);
    }

    @Test
    public void t11() {
        AssetsPermission assetsPermission = new AssetsPermission();
        assetsPermission.setName("SDK授权规则");
        assetsPermission.setSystem_users(new String[]{"c9437cf5-3266-458c-a968-69c6c89e3a3e"});
//        assetsPermission.setUser_groups(new String[]{"a9eb1158-ee23-4b62-815f-91f342bc66f3"});
        assetsPermission.setUsers(new String[]{"a1c94669-1c4c-47a8-ab27-9092b3dc4363"});
//        assetsPermission.setNodes(new String[]{"3e62f4e4-4902-4754-8916-c3b3b0503c19"});
        assetsPermission.setAssets(new String[]{"d1bda44a-2986-414d-adc3-881718b3a0ae"});
        Map<String, String> map = jmsPermissionService.addAssetpermission(assetsPermission);
        System.out.println(map);
    }

    @Test
    public void t12() {
        AssetsPermission assetsPermission = new AssetsPermission();
        assetsPermission.setId("5becc8d4-fbfd-4ad0-b2f5-07c806b9359b");
        assetsPermission.setName("SDK授权规则-modify");
        Map<String, String> map = jmsPermissionService.updateAssetpermission(assetsPermission);
        System.out.println(map);
    }

    @Test
    public void t13() {
        String id = "5becc8d4-fbfd-4ad0-b2f5-07c806b9359b";
        Map<String, String> map = jmsPermissionService.queryAssetpermission(id);
        System.out.println(map);
    }

}
