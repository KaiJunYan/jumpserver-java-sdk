package client;

import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
import com.jumpserver.sdk.v2.httpclient.build.Config;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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

}
