package jms;

import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.model.AdminUser;
import org.junit.Test;
import java.util.List;

/**
 * 机器管理用户API调用相关测试用例
 */
public class JmsAdminUserTest extends CommonBeforeTest{

    private String adminUserId = "6bd004b1-e77e-4a1b-b323-62b436d64375";

    @Test
    public void AdminUser() {
        List<AdminUser> list = os.assets().listAdminUser();
        System.out.println("admin user size :: " + list.size());
        for (AdminUser object : list) {
            System.out.println(object.getId() + " : " + object.getName());
        }
    }

    @Test
    public void addAdminUser() {
        AdminUser object = new AdminUser();
        object.setId(adminUserId);
        object.setName("adminUser");
        AdminUser objectBack = os.assets().createAdminUser(object);
        System.out.println(objectBack.getId());
    }

    @Test
    public void updateAdminUser() {
        AdminUser object = new AdminUser();
        object.setId(adminUserId);
        object.setName("adminUser-modify");
        AdminUser objectBack = os.assets().updateAdminUser(object);
        System.out.println(objectBack.getId());
    }

    @Test
    public void getAdminUser() {
        AdminUser object = os.assets().getAdminUser(adminUserId);
        System.out.println(object.getName());
    }

    @Test
    public void updateAdminUserAuth() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(adminUserId);
        adminUser.setPassword("psssword");
        adminUser.setPrivate_key("-----BEGIN RSA PRIVATE KEY-----\n" +
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
        AdminUser user = os.assets().updateAdminUserAuthInfo(adminUser);
        System.out.println(user.getName());
    }

    @Test
    public void deleteAdminUser() {
        ActionResponse delete = os.assets().deleteAdminUser(adminUserId);
        System.out.println(delete.getCode());
    }

}
