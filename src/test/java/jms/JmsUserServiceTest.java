package jms;

import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
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
    private String password;
    private String orgId;


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
            password = (String) properties.get("password");
            orgId = (String) properties.get("orgId");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientBuilder credentials = new ClientBuilder()
                .endpoint(endPoint)
                .credentials(username, password);
        if (StringUtils.isBlank(orgId)) {
            os = credentials.authenticate();
        } else {
            os = credentials.header("x-jms-org", orgId).authenticate();
        }
        System.out.println("JmsUserServiceTest get token:" + os.getToken().getToken());
    }

    @Test
    public void addUserGroups() {
        System.out.println("add userGroup::::");
        UserGroup usergroup = new UserGroup();
        usergroup.setId(userGroupId);
        usergroup.setName("B组");
        UserGroup userGroupBack = os.users().createUserGroup(usergroup);
        System.out.println(userGroupBack.getId());
        System.out.println(userGroupBack.getName());
    }

    @Test
    public void updateUserGroups() {
        System.out.println("update userGroup::::");
        UserGroup usergroup = new UserGroup();
        usergroup.setName("A组");
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
    public void getByName() {
        System.out.println("getByName user:::");
        List<User> users = os.users().getByName("bbb");
        for (User user : users) {
            System.out.println(user.getName() + " :: " + user.getEmail());
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
