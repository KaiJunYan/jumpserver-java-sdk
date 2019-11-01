package jms;

import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
import com.jumpserver.sdk.v2.common.ClientConstants;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonBeforeTest {

    protected JMSClient os;
    protected String orgId;
    private String endPoint;
    private String keyId;
    private String keySecret;

    @Before
    public void initClient() {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/credential.property");
            properties.load(resourceAsStream);
            endPoint = (String) properties.get("endPoint");
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
}
