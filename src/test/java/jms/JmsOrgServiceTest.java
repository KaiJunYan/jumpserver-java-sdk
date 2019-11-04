package jms;

import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.model.Org;
import com.jumpserver.sdk.v2.model.OrgUsers;
import org.junit.Test;

import java.util.List;

/**
 * 组织API调用相关测试用例
 */
public class JmsOrgServiceTest extends CommonBeforeTest{

    private String orgIdTest = "0e049ec7-d905-466b-bcfb-5a66334cae0c1";
    private  String userId = "42f1251e-70f8-4e63-a4e9-885eb1f696a7";

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
        object.setOrgId("48afff20-32ce-43b7-8a13-5cd750acf989");
        object.setUser("9ef25019-f01d-4865-8daf-07d27cb873ff");
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
