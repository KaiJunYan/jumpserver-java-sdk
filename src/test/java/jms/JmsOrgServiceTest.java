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

    private String orgIdTest = "48afff20-32ce-43b7-8a13-5cd750acf989";
    private  String userId = "415041b3-650c-49fe-adef-d8e36d54844e";

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
        object.setOrgId(orgIdTest);
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
        ActionResponse actionResponse = os.orgs().deleteOrgUsers(orgIdTest, userId);
        System.out.println(actionResponse.toString());
    }


    @Test
    public void createOrgAdmins() {
        OrgUsers object = new OrgUsers();
        object.setOrgId(orgIdTest);
        object.setUser(userId);
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
        ActionResponse actionResponse = os.orgs().deleteOrgAdmins(orgIdTest, userId);
        System.out.println(actionResponse.toString());
    }

    @Test
    public void deleteOrg() {
        ActionResponse delete = os.orgs().deleteOrg(orgId);
        System.out.println(delete);
    }


}
