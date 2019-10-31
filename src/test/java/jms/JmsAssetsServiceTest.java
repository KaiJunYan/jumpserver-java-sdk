package jms;

import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.model.*;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class JmsAssetsServiceTest {

    private JMSClient os;
    private String endPoint;
    private String username;
    private String orgId;
    private String keyId;
    private String keySecret;

    private String assetId = "9a4aadd7-3aab-4a67-b191-52298ef6d2fb";

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
                .credentials(username, keyId, keySecret);
        if (StringUtils.isBlank(orgId)) {
            os = credentials.authenticate();
        } else {
            os = credentials.header(ClientConstants.X_JMS_ORG, orgId).authenticate();
        }
    }

    //=========================== 资产 =============================
    @Test
    public void addAsset() {
        Asset asset = new Asset();
        asset.setId(assetId);
        asset.setIp("192.168.20.102");
        asset.setHostname("欧洲十国游");
        Asset assetnew = os.assets().create(asset);
        System.out.println(assetnew.getId());
    }

    @Test
    public void getAsset() {
        Asset asset = os.assets().get(assetId);
        System.out.println(asset.getHostname());
        System.out.println(asset.getAdmin_user());
    }

    @Test
    public void assets() {
        List<Asset> assetList = os.assets().list();
        System.out.println("assets size: " + assetList.size());
        for (Asset asset : assetList) {
            System.out.println(asset.getId());
            System.out.println(asset.getIp());
            System.out.println(asset.getHostname());
            System.out.println(StringUtils.join(asset.getNodes()));
        }
    }

    @Test
    public void updateAsset() {
        Asset asset = new Asset();
        asset.setId(assetId);
        asset.setIp("192.168.20.102");
        asset.setHostname("欧洲十一国游");
        Asset assetnew = os.assets().update(asset);
        System.out.println(assetnew.getId());
    }

    @Test
    public void deleteAsset() {
        ActionResponse delete = os.assets().delete(assetId);
        System.out.println(delete);
    }

    //=========================== 资产树 =============================
    @Test
    public void addAssetsNode() {
        AssetsNode object = new AssetsNode();
        object.setId(assetId);
        object.setValue("演示工作空间-son");
        AssetsNode objectBack = os.assets().createAssetsNode(object);
        System.out.println(objectBack.getId());
    }

    @Test
    public void getAssetsNode() {
        AssetsNode object = os.assets().getAssetsNode(assetId);
        System.out.println(object.getValue());
    }

    @Test
    public void updateAssetsNode() {
        AssetsNode object = new AssetsNode();
        object.setId(assetId);
        object.setValue("docker_tmp");
        AssetsNode objectBack = os.assets().updateAssetsNode(object);
        System.out.println(objectBack.getId());
    }

    @Test
    public void AssetsNodes() {
        List<AssetsNode> list = os.assets().listAssetsNode();
        System.out.println(list.size());
        for (AssetsNode object : list) {
            System.out.println(object.getId());
            System.out.println(object.getTree_parent());
            System.out.println(object.getValue());
            System.out.println(object.getIs_node());
        }
    }

    @Test
    public void deleteAssetsNode() {
        ActionResponse delete = os.assets().deleteAssetsNode(assetId);
        System.out.println(delete);
    }

    //=========================== 标签 =============================
//    @Test
//    public void lb1() {
//        AssetsLabel assetsLabel = new AssetsLabel();
//        assetsLabel.setName("SDK-Name");
//        assetsLabel.setValue("SDK_value");
//        Map<String, String> map = jmsAssetsService.addAssetsLabel(assetsLabel);
//        System.out.println(map);
//    }

//    @Test
//    public void lb2() {
//        AssetsLabel assetsLabel = new AssetsLabel();
//        assetsLabel.setName("SDK-Name-Modify");
//        assetsLabel.setValue("SDK-Name-Value");
//        assetsLabel.setId("43ce643d-df31-4267-aadb-b9db3afec68b");
//        Map<String, String> map = jmsAssetsService.updateAssetsLabel(assetsLabel);
//        System.out.println(map);
//    }

}
