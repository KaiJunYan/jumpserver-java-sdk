package jms;

import com.alibaba.fastjson.JSON;
import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.model.User;
import com.jumpserver.sdk.v2.model.UserGroup;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 用户API调用相关测试用例
 */
public class JmsUserServiceTest extends CommonBeforeTest {

    private String userGroupId = "90b20128-c92c-4d69-9a18-68d9636b7ac1";
    private String userId = "570cd13a-84dd-4710-9385-99ea3ad69999";

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
        user.setGroups(new String[]{});
        user.setName("sdk test");
        user.setUsername("sdk");
        user.setEmail("sdk@fit2cloud.com");
        try {
            User user1 = os.users().create(user);
            System.out.println(user1.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        os.users().list().stream().map(User::getName).forEach(System.out::println);

        os.users().search("kaijun@fit2cloud.com").stream().map(User::getEmail).forEach(System.out::println);
    }

    @Test
    public void search() throws UnsupportedEncodingException {
        System.out.println("search user:::");
        String name = URLEncoder.encode("管理员", "UTF-8");
//        String  name = URLEncoder.encode("admin", "UTF-8");
        List<User> users = os.users().search(name);
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
        System.out.println("list users:::");
        List<User> users = os.users().list();
        System.out.println(users.size());
        for (User user : users) {
            System.out.println(user.getName() + " :: " + user.getEmail());
        }
    }

    @Test
    public void get() {
        System.out.println("get user:::");
        User user = os.users().get(userId);
        System.out.println(user.getName() + " :: " + user.getEmail());
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
