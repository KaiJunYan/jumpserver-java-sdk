package client;

import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.httpclient.build.Config;
import com.jumpserver.sdk.v2.model.UserGroup;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author yankaijun
 * @date 2018/10/15 下午4:26
 */
public class JMSClientTest {

    private String endPoint;
    private String username;
    private String password;
    private String orgId;

    @Before
    public void prepare() {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/credential.property");
            //InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("credential.property");
            properties.load(resourceAsStream);
            endPoint = (String) properties.get("endPoint");
            username = (String) properties.get("username");
            password = (String) properties.get("password");
            orgId = (String) properties.get("orgId");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void token() {
        try {
            JMSClient os;
            ClientBuilder credentials = new ClientBuilder()
                    .endpoint(endPoint)
                    .credentials(username, password)
                    .withConfig(Config.newConfig().withConnectionTimeout(10000).withReadTimeout(10000));
            if (StringUtils.isBlank(orgId)) {
                os = credentials.authenticate();
            } else {
                os = credentials.header("x-jms-org", orgId).authenticate();
            }
            System.out.println("=======JMSClientTest======");
            System.out.println(os.getToken());
            System.out.println(os.getToken());
            System.out.println(os.getToken().getUser().getId());
            System.out.println(os.getToken().getUser().getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tokenWithOrgChange() {
        JMSClient client = new ClientBuilder()
                .endpoint(endPoint)
                .credentials(username, password)
                .header("x-jms-org","2107470f-1107-4352-84fc-8de6ef3b7fe8")
                .withConfig(Config.newConfig().withConnectionTimeout(10000).withReadTimeout(10000)).authenticate();
        System.out.println(client.getHeaders());
        List<UserGroup> userGroups = client.users().userGroups();
        userGroups.stream().forEach(u -> System.out.println(u.getName()));

        //切换组织
        client.getHeaders().put("x-jms-org", "84d8a23e-5bd9-410e-9438-756054daf128");
        System.out.println(client.getHeaders());
        List<UserGroup> userGroups2 = client.users().userGroups();
        userGroups2.stream().forEach(u -> System.out.println(u.getName()));
    }
}
