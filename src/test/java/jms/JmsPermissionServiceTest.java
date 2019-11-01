package jms;

import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.model.AssetsPermission;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class JmsPermissionServiceTest {

    private JMSClient os;
    private String endPoint;
    private String username;
    private String orgId;
    private String keyId;
    private String keySecret;

    private String permissionId = "41864956-6d00-4e96-b7fd-33ae4adf2643";

    @Before
    public void token() {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/credential.property");
            properties.load(resourceAsStream);
            endPoint = (String) properties.get("endPoint");
            username = (String) properties.get("username");
            keyId = (String) properties.get("keyId");
            keySecret = (String) properties.get("keySecret");
            orgId = (String) properties.get("orgId");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientBuilder credentials = new ClientBuilder()
                .endpoint(endPoint)
                .credentials( keyId, keySecret);
        if (StringUtils.isBlank(orgId)) {
            os = credentials.authenticate();
        } else {
            os = credentials.header(ClientConstants.X_JMS_ORG, orgId).authenticate();
        }
    }

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
