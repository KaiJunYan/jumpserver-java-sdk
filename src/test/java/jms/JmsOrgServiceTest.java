package jms;

import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.model.Org;
import com.jumpserver.sdk.v2.model.OrgUsers;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author yankaijun
 * @date 2018/10/26 12:00 PM
 */
public class JmsOrgServiceTest {
    private JMSClient os;
    private String endPoint;
    private String username;
    private String orgId;
    private String keyId;
    private String keySecret;


    private String orgIdTest = "0e049ec7-d905-466b-bcfb-5a66334cae0c1";
    private  String userId = "42f1251e-70f8-4e63-a4e9-885eb1f696a7";
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

    @Test
    public void addOrg() {
        Org object = new Org();
        object.setName("X-Org");
        Org objectBack = os.orgs().createOrg(object);
        System.out.println(objectBack.getId());
    }

    @Test
    public void getOrg() {
        Org object = os.orgs().getOrg(orgId);
        System.out.println(object.getName());
    }

    @Test
    public void updateOrg() {
        Org object = new Org();
        object.setId(orgId);
        object.setName("API");
        Org objectBack = os.orgs().updateOrg(object);
        System.out.println(objectBack.getId());
        System.out.println(objectBack.getName());
    }

    @Test
    public void Org() {
        List<Org> list = os.orgs().listOrg();
        System.out.println(list.size());
        for (Org object : list) {
            System.out.println(object.getId());
            System.out.println(object.getName());
        }
    }


    @Test
    public void createOrgUsers() {
        OrgUsers object = new OrgUsers();
        object.setOrgId(orgId);
        object.setUser(userId);
        OrgUsers objectBack = os.orgs().createOrgUsers(object);
        System.out.println(objectBack.getUser());
    }

    @Test
    public void getOrgUses() {
        List<OrgUsers> object = os.orgs().getOrgUsers(orgId);
        for (OrgUsers users : object) {
            System.out.println(users.getUser());
        }
    }

    @Test
    public void deleteOrgUses() {
        ActionResponse actionResponse = os.orgs().deleteOrgUsers(orgId, userId);
        System.out.println(actionResponse.toString());
    }


    @Test
    public void createOrgAdmins() {
        OrgUsers object = new OrgUsers();
        object.setOrgId(orgId);
        object.setUser("0e049ec7-d905-466b-bcfb-5a66334cae0c");
        OrgUsers objectBack = os.orgs().createOrgAdmins(object);
        System.out.println(objectBack.getUser());
    }

    @Test
    public void getOrgAdmins() {
        List<OrgUsers> object = os.orgs().getOrgAdmins(orgId);
        for (OrgUsers users : object) {
            System.out.println(users.getUser());
        }
    }

    @Test
    public void deleteOrgAdmins() {
        ActionResponse actionResponse = os.orgs().deleteOrgAdmins(orgId, userId);
        System.out.println(actionResponse.toString());
    }

    @Test
    public void deleteOrg() {
        ActionResponse delete = os.orgs().deleteOrg(orgId);
        System.out.println(delete);
    }


}
