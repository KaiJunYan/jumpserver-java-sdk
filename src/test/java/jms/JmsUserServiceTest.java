package jms;

import com.alibaba.fastjson.JSON;
import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.model.User;
import com.jumpserver.sdk.v2.model.UserGroup;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class JmsUserServiceTest {

    private JMSClient os;
    private String endPoint;
    private String username;
    private String orgId;
    private String keyId;
    private String keySecret;


    private String userGroupId= "90b20128-c92c-4d69-9a18-68d9636b7ac1";
    private String userId= "570cd13a-84dd-4710-9385-99ea3ad69999";

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
    public void addUserGroups() {
        System.out.println("add userGroup::::");
        UserGroup usergroup = new UserGroup();
        usergroup.setId(userGroupId);
        usergroup.setName("T组");
        UserGroup userGroupBack = os.users().createUserGroup(usergroup);
        System.out.println(userGroupBack.getId());
        System.out.println(userGroupBack.getName());
    }

    @Test
    public void updateUserGroups() {
        System.out.println("update userGroup::::");
        UserGroup usergroup = new UserGroup();
        usergroup.setName("X组");
        usergroup.setId(userGroupId);
        UserGroup userGroupBack = os.users().updateUserGroup(usergroup);
        System.out.println(userGroupBack.getId());
        System.out.println(userGroupBack.getName());
    }

    @Test
    public void userGroups() {
        System.out.println("list userGroup::::");
        List<UserGroup> usergroups = os.users().userGroups();
        System.out.println(usergroups.size());
        for (UserGroup usergroup : usergroups) {
            System.out.println(usergroup.getName());
        }
    }


    @Test
    public void addUser() {
        System.out.println("add user:::");
        User user = new User();
        user.setId(userId);
        user.setGroups(new String[]{userGroupId});
        user.setName("sdk test");
        user.setUsername("sdk");
        user.setEmail("sdk@fit2cloud.com");
        User user1 = os.users().create(user);
        System.out.println(user1.getId());
    }

    @Test
    public void search() {
        System.out.println("search user:::");
        List<User> users = os.users().search("kaijun@fit2cloud.com");
        for (User user : users) {
            System.out.println(JSON.toJSON(user));
        }
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(userId);
        user.setName("sdkUpdate");
        user.setUsername("sdkUpdate");
        user.setEmail("sdk2@fit2cloud.com");
        User user1 = os.users().update(user);
        System.out.println(user1.getEmail());
        System.out.println(user1.getName());
        System.out.println(user1.getUsername());
    }

    @Test
    public void users() {
        System.out.println("list user:::");
        List<User> users = os.users().list();
        System.out.println(users.size());
        for (User user : users) {
            System.out.println(user.getName() + " :: " + user.getEmail());
        }
    }

    @Test
    public void changePassword() {
        ActionResponse admin = os.users().changePassword(userId, "admin123");
        System.out.println(admin);
    }

    @Test
    public void deleteUserGroups() {
        System.out.println("delete userGroup::::");
        ActionResponse actionResponse = os.users().deleteUserGroup(userGroupId);
        System.out.println(actionResponse);
    }

    @Test
    public void deleteUser() {
        ActionResponse delete = os.users().delete(userId);
        System.out.println(delete.toString());
    }

}
