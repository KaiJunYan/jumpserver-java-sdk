package jms;

import com.jumpserver.sdk.v2.builder.ClientBuilder;
import com.jumpserver.sdk.v2.builder.JMSClient;
import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.model.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class JmsSystemUserTest {

    private JMSClient os;
    private String endPoint;
    private String username;
    private String orgId;
    private String keyId;
    private String keySecret;


    private String orgUserId = "fd8d3e80-7820-4c1d-9fd9-3d80e35489b5";
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
    public void SystemUser() {
        List<SystemUser> list = os.assets().listSystemUser();
        System.out.println(list.size());
        for (SystemUser object : list) {
            System.out.println(object.getId());
            System.out.println(object.getName());
        }
    }

    @Test
    public void addSystemUser() {
        SystemUser systemUser = new SystemUser();
        systemUser.setName("SDK-Name");
        systemUser.setUsername("SDK-Name");
        SystemUser objectBack = os.assets().createSystemUser(systemUser);
        System.out.println(objectBack.getId());
    }

    @Test
    public void getSystemUser() {
        SystemUser object = os.assets().getSystemUser(orgUserId);
        System.out.println(object.getName());
    }

    @Test
    public void updateSystemUser() {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(orgUserId);
        systemUser.setName("SDK-Name-Modify");
        systemUser.setUsername("SDK-Name-Modify");
        SystemUser objectBack = os.assets().updateSystemUser(systemUser);
        System.out.println(objectBack.getId());
    }

    @Test
    public void updateSystemUserAuthInfo() {
        SystemUser systemUser = new SystemUser();
        systemUser.setId("b121d937-f948-4fb2-bfd6-4728da4e32ea");
        systemUser.setUsername("aaa");
        systemUser.setPassword("password");
        systemUser.setPrivate_key("-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIEowIBAAKCAQEAjcSD0EGmfTyexO4jDMF8Orw/eXM4t+rSzXDmzP1n8iqahhVV\n" +
                "cHb/6keBgdbf2bFTZUpJJDHTLTNMzmCqfdOV//qDQ5gyO8ntz8VAPneqpB05qEgz\n" +
                "krTkpALEP/YmDKIXVp/iwdKf2kMI1neWWQ19eI0HIaxIZ9qhzbCAf49MyITEeif9\n" +
                "dN0kABF7MFpoFb258+u2I7zl92YkBTdzZieJsXjrTxqV9OmvacqS+U5F9SlYU+O3\n" +
                "nG1wjhPaq7nwmki9vLrfhpQpLs2I+imRl/KJxwiuzSMlkxUVfjEmlmntvDTptv3L\n" +
                "k5A+XD0aDeJNTdwfAlWQyhWZiWt555Pw1RGl1QIDAQABAoIBAQCK8mWi8wVjz8tp\n" +
                "BmHx+Z1xz9mChoy7rfroglSj62Y6ssj6y+QOs4OGMolbIagKrEMpjpJ7WB47OgF4\n" +
                "cMQJSRzGY9jNoljxwqtDrXeXAiCMc1x0dDef/Y3utgt1CSMn1KJ9M33DLFWiTXtX\n" +
                "AKSqKY+lXMv1k+7vYxidCfYXj/LYv9XdBaI/dLDembFfayyudIQ6CaV6uVRHVV8W\n" +
                "4cQxsY4yw16Y6Mebpfrs5EJLH9l1cUX0bA/qdL1pr+N7B2a5j1RAndCn5SFGiD3N\n" +
                "axZYRl6gdn0Ea5t76SueKpQXzE1JjAFjpilB8a0iveuCnwRXflAf7RjX5S4UuW9p\n" +
                "KbH8hR2hAoGBAN8UusO1lzQBCG2OJ6xazSFfUMV63fuXGPYUrQpDdb9WN7/aEpEF\n" +
                "elZZt+30wwE+8NkoWzDYoNbIu7DKmrMnyrHy6iW13BQKpUOo5Fv466VbrNCEvTzI\n" +
                "3S7Cdsuao+X30g76due4FFSASjR5XuA/oMmFWU76sEC9fGjJwH8YXMSdAoGBAKKw\n" +
                "CHac+3ab5urqYJDpXrhqFJ99W6IrHM5NgsIcGXkx1ciEKKNxy0RXvTsXARXMJFcu\n" +
                "TJZYlkBFYIdJo1QAU+mFdLg/BbkmRYPUy8O48A9NzVA4ae1V1eBFgGfEdyAROKPv\n" +
                "9BfGot4MaohA9JokM3//G1U2J1g9MQOkppM13XSZAoGAe2o7fQScdx/PKtUHa9n8\n" +
                "PGdAB1DXOFY6cAG2TcROgyFOzGv385VFPjp6gEH2FF1e37Ts5lQif1EkTUV5Wn48\n" +
                "LECIe35mGAxfq9PnKl7G93+FVwLospUpkLHzCOcvn41//9tfJwj27lzfMLKo44Uw\n" +
                "De0hIImeMuLhzhM31NiEkh0CgYBiooOfFnJHaSJJzywY78Sj3nz+bYx+99CRK+FZ\n" +
                "OpwfJJ3O4oGZBscMen2aM8pOouRtjv6UMMV31hg+LeE+bqxxap5njp4zsQZ4dS3N\n" +
                "TnUNckhGmM1wPZhcTwcY4r6uzIIgsry1JZNdOP3l+ivSwJwQwGSb9ydHkmIDKXJs\n" +
                "0j2gEQKBgFXM7mldHEhiTDFByjzRZV9GHcZTi+6V+5ckzA9j3aCzTvKEYOg6U4b3\n" +
                "0znw0cNna5ACZZ7hKWwFXXGqjoJmn6KmwMhRM/l0g6BqBi7Cpkfaqyl3SgvLA0zu\n" +
                "ipUckOJc2ik7ZwLqe3IzjK0kaxcN1sr0zmWtLF+Hcdy7RwMkZF25\n" +
                "-----END RSA PRIVATE KEY-----");
        SystemUser user = os.assets().updateSystemUserAuthInfo(systemUser);
        System.out.println(user.getName());
    }



    @Test
    public void systemUserPush() {
        SystemUser user = os.assets().updateSystemUserPush(orgUserId);
        System.out.println(user.getName());
    }

    @Test
    public void deleteSystemUser() {
        ActionResponse delete = os.assets().deleteSystemUser(orgUserId);
        System.out.println(delete);
    }



}
