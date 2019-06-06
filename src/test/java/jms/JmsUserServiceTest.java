package jms;

import com.jumpserver.sdk.model.User;
import com.jumpserver.sdk.model.Usergroup;
import com.jumpserver.sdk.service.JmsUsersService;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class JmsUserServiceTest {


    private   JmsUsersService jmsUsersService;
    @Before
    public void before() {
         jmsUsersService = new JmsUsersService("http://localhost", "admin", "admin");
    }

    @Test
    public void testGetToken() {
        JmsUsersService jmsUsersService = new JmsUsersService("http://localhost", "admin", "admin");
        String token = jmsUsersService.getToken();
        System.out.println(token);
    }

    @Test
    public void t12() {
        JmsUsersService jmsUsersService = new JmsUsersService("http://localhost:8080", "admin", "admin");
//        String token = jmsUsersService.getToken();
//        System.out.println(token);
        User user = new User();
        user.setUsername("王大锤");
        user.setName("王大锤");
        user.setEmail("wdc@qq.com");
        user.setGroups(new String[]{});
        Map<String, String> map = jmsUsersService.addUser(user);
        System.out.println(map.toString());
    }

    @Test
    public void t13() {
        JmsUsersService jmsUsersService = new JmsUsersService("http://localhost:8080", "admin", "admin");
        String token = jmsUsersService.getToken();
        System.out.println(token);
        User user = new User();
        user.setId("363c5584-6331-4b82-a595-e2844635f766");
        user.setGroups(new String[]{"5a16dd3c-17d5-44de-a796-32ee442acdcf"});
        Map<String, String> map = jmsUsersService.updateUser(user);
        System.out.println(map.toString());
    }

    @Test
    public void t14() {
        JmsUsersService jmsUsersService = new JmsUsersService("http://localhost:8080", "admin", "admin");
        String token = jmsUsersService.getToken();
        System.out.println(token);
        User user = new User();
        user.setId("363c5584-6331-4b82-a595-e2844635f766");
        Map<String, String> map = jmsUsersService.deleteUser(user);
        System.out.println(map.toString());
    }

    @Test
    public void t15() {
        JmsUsersService jmsUsersService = new JmsUsersService("http://localhost", "admin", "admin");
        String token = jmsUsersService.getToken();
        System.out.println(token);
//        User user = new User();
//        user.setId("9e39700c-6a7b-4f7d-9c8f-2ed52e1b8faf");
//        Map<String, String> map = jmsUsersService.queryUser("9e39700c-6a7b-4f7d-9c8f-2ed52e1b8faf");
        Map<String, String> map = jmsUsersService.queryUser("4947bb2b-ccc6-4d53-a3ac-21083c4b022a");
        System.out.println(map.toString());
    }

    @Test
    public void t21() {
        Usergroup usergroup  =  new Usergroup();
        usergroup.setName("sdk用户组");
        Map<String, String> map = jmsUsersService.addUserGroup(usergroup);
        System.out.println(map.toString());
    }


    @Test
    public void t22() {
        JmsUsersService jmsUsersService = new JmsUsersService("http://localhost:8080", "admin", "admin");
        String token = jmsUsersService.getToken();
        System.out.println(token);
        Usergroup usergroup  =  new Usergroup();
        usergroup.setId("4eb30422-db30-4edd-807f-bd4debc1e3e2");
        usergroup.setName("sdk用户组-modify");
        Map<String, String> map = jmsUsersService.updateUserGroup(usergroup);
        System.out.println(map.toString());
    }


    @Test
    public void t23() {
        JmsUsersService jmsUsersService = new JmsUsersService("http://localhost:8080", "admin", "admin");
        String token = jmsUsersService.getToken();
        System.out.println(token);
        Usergroup usergroup  =  new Usergroup();
        usergroup.setId("4eb30422-db30-4edd-807f-bd4debc1e3e2");
        Map<String, String> map = jmsUsersService.deleteUserGroup(usergroup);
        System.out.println(map.toString());
    }

    @Test
    public void t24() {
        JmsUsersService jmsUsersService = new JmsUsersService("http://localhost:8080", "admin", "admin");
        String token = jmsUsersService.getToken();
        System.out.println(token);
        User user = new User();
        user.setId("f29eaa54-c861-4f62-8764-4e3197b2a242");
        user.setPassword("aaa");
        Map<String, String> map = jmsUsersService.userPasswordReset(user);
        System.out.println(map.toString());
    }


}
