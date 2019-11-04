package jms;

import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.model.AssetsPermission;
import org.junit.Test;
import java.util.List;

/**
 * 授权API调用相关测试用例
 */
public class JmsPermissionServiceTest extends CommonBeforeTest{

    private String permissionId = "41864956-6d00-4e96-b7fd-33ae4adf2643";

    @Test
    public void addAssetsPermission() {
        AssetsPermission object = new AssetsPermission();
        object.setSystem_users(new String[]{"6ca16c2b-77ec-4757-a100-ddbde4c1a8c4"});
        object.setName("API授权规则");
        AssetsPermission objectBack = os.permissions().createAssetsPermission(object);
        System.out.println(objectBack.getId());
    }

    @Test
    public void getAssetsPermission() {
        AssetsPermission object = os.permissions().getAssetsPermission(permissionId);
        System.out.println(object.getName());
    }

    @Test
    public void updateAssetsPermission() {
        AssetsPermission object = new AssetsPermission();
        object.setId(permissionId);
        object.setName("niubi");
        AssetsPermission objectBack = os.permissions().updateAssetsPermission(object);
        System.out.println(objectBack.getId());
    }

    @Test
    public void AssetsPermission() {
        List<AssetsPermission> list = os.permissions().list();
        System.out.println(list.size());
        for (AssetsPermission object : list) {
            System.out.println(object.getId());
            System.out.println(object.getName());
        }
    }

    @Test
    public void deleteAssetsPermission() {
        ActionResponse delete = os.permissions().deleteAssetsPermission(permissionId);
        System.out.println(delete);
    }

}
