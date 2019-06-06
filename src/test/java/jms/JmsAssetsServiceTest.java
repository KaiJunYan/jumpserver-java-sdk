package jms;

import com.jumpserver.sdk.model.*;
import com.jumpserver.sdk.service.JmsAssetsService;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class JmsAssetsServiceTest {

    private JmsAssetsService  jmsAssetsService;

    @Before
    public void getJmsAssetsService() {
        jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
    }

    //资产
    @Test
    public void z1() {
        Asset asset = new Asset();
        asset.setHostname("SDK-hostName");
        asset.setIp("192.168.20.101");
        asset.setType("VM");
        Map<String, String> map = jmsAssetsService.addAsset(asset);
        System.out.println(map);
    }

    @Test
    public void z2() {
        Asset asset = new Asset();
        asset.setHostname("SDK-hostName-modify");
        asset.setId("b003bdf1-d820-47e4-8b1b-2802528e3aca");
        Map<String, String> map = jmsAssetsService.updateAsset(asset);
        System.out.println(map);
    }

    @Test
    public void z3() {
        Asset asset = new Asset();
        asset.setId("b003bdf1-d820-47e4-8b1b-2802528e3aca");
        Map<String, String> map = jmsAssetsService.updateAsset(asset);
        System.out.println(map);
    }

    //资产树
    @Test
    public void node1() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
        AssetsNodes assetsNodes = new AssetsNodes();
        assetsNodes.setValue("java");
        Map<String, String> map = jmsAssetsService.addAssetsNodes(assetsNodes);
        System.out.println(map.toString());
    }

    @Test
    public void node2() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
        AssetsNodes assetsNodes = new AssetsNodes();
        assetsNodes.setValue("java-modify");
        assetsNodes.setId("d211fcdd-7f59-4b9f-815f-f2b2e4ec98d4");
        Map<String, String> map = jmsAssetsService.updateAssetsNodes(assetsNodes);
        System.out.println(map.toString());
    }

    @Test
    public void node3() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
        Map<String, String> map = jmsAssetsService.queryAllAssetsNodes();
        System.out.println(map.toString());
    }

    @Test
    public void node4() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
        AssetsNodes assetsNodes = new AssetsNodes();
        assetsNodes.setId("6d9bbc623ea344e1abdf3f75adce9d3f");
        Map<String, String> map = jmsAssetsService.deleteAssetsNodes(assetsNodes);
        System.out.println(map.toString());
    }

    @Test
    public void nodeChildren() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
        Map<String, String> map = jmsAssetsService.queryAssetsNodesChildren("d211fcdd-7f59-4b9f-815f-f2b2e4ec98d4");
        System.out.println(map.toString());
    }

    @Test
    public void nodeChildren2() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
        AssetsNodes assetsNodes = new AssetsNodes();
        assetsNodes.setValue("children");
        Map<String, String> map = jmsAssetsService.addAssetsNodesChildren("d211fcdd-7f59-4b9f-815f-f2b2e4ec98d4",assetsNodes);
        System.out.println(map.toString());
    }

    @Test
    public void nodeChildren3() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        AssetsNodes assetsNodes = new AssetsNodes();
        assetsNodes.setNodes(new String[]{"0e321c37-dd1a-446d-924b-773852f29cbf"});
        Map<String, String> map = jmsAssetsService.updateAssetsNodesChildren("af034e7e-a74c-4b2a-b473-21208eb0a48d",assetsNodes);
        System.out.println(map.toString());
    }

    @Test
    public void nodeAseets1() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
        String [] ass = {"47cc75f8-2218-4ac3-9575-686f69989ce5"};
        AssetsNodes assetsNodes = new AssetsNodes();
        assetsNodes.setAssets(ass);
        assetsNodes.setValue("addAssetsNodesAsset");
        Map<String, String> map = jmsAssetsService.addAssetsNodesAsset(assetsNodes,"894daf04d7cc47928c7ad3d8ae82bcd0");
        System.out.println(map.toString());
    }

    @Test
    public void nodeAseets2() {
        JmsAssetsService  jmsAssetsService= new JmsAssetsService("http://localhost:8080", "admin", "admin");
        String token = jmsAssetsService.getToken();
        System.out.println(token);
        String [] ass = {"38db393c747f4237838cecd944975362"};
        AssetsNodes assetsNodes = new AssetsNodes();
        assetsNodes.setAssets(ass);
        Map<String, String> map = jmsAssetsService.removeAssetsNodesAsset(assetsNodes,"3e62f4e4-4902-4754-8916-c3b3b0503c19");
        System.out.println(map.toString());
    }



    //管理用户
    @Test
    public void au1() {
        AdminUser adminUser = new AdminUser();
        adminUser.setName("SDK-Name");
        adminUser.setUsername("SDK-Name");
        Map<String, String> map = jmsAssetsService.addAdminUser(adminUser);
        System.out.println(map);
    }

    @Test
    public void au2() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId("e40d3bc6-544f-422d-bcb1-333993a71bd7");
        adminUser.setName("SDK-Name-Modify");
        adminUser.setUsername("SDK-Name-Modify");
        Map<String, String> map = jmsAssetsService.updateAdminUser(adminUser);
        System.out.println(map);
    }

    @Test
    public void au3() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId("e40d3bc6-544f-422d-bcb1-333993a71bd7");
        adminUser.setClusters(new String[]{});
        Map<String, String> map = jmsAssetsService.updateAdminUserCluster(adminUser);
        System.out.println(map);
    }

    @Test
    public void au4() {
        AdminUser adminUser = new AdminUser();
        adminUser.setId("792732ce-9cd0-4cb9-af25-37cb843356f1");
        adminUser.setPassword("pssswofdd");
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
        Map<String, String> map = jmsAssetsService.updateAdminUserAuth(adminUser);
        System.out.println(map);
    }

    //系统用户
    @Test
    public void su1() {
        SystemUser systemUser = new SystemUser();
        systemUser.setName("SDK-Name");
        systemUser.setUsername("SDK-Name");
        Map<String, String> map = jmsAssetsService.addSystemUser(systemUser);
        System.out.println(map);
    }

    @Test
    public void su2() {
        SystemUser systemUser = new SystemUser();
        systemUser.setId("43ce643d-df31-4267-aadb-b9db3afec68b");
        systemUser.setName("SDK-Name-Modify");
        systemUser.setUsername("SDK-Name-Modify");
        Map<String, String> map = jmsAssetsService.updateSystemUser(systemUser);
        System.out.println(map);
    }
    @Test
    public void su3() {
        Map<String, String> map = jmsAssetsService.querySystemUserAuthInfo("8caf675e-d341-48e3-b00f-24206fb3c208");
        System.out.println(map);
    }
    @Test
    public void su4() {
        SystemUser systemUser = new SystemUser();
        systemUser.setId("ece3101e-317e-459e-a315-c92283c4d146");
        systemUser.setUsername("username");
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
        Map<String, String> map = jmsAssetsService.updateSystemUserAuthInfo(systemUser);
        System.out.println(map);
    }



    //标签
    @Test
    public void lb1() {
        AssetsLabel assetsLabel = new AssetsLabel();
        assetsLabel.setName("SDK-Name");
        assetsLabel.setValue("SDK_value");
        Map<String, String> map = jmsAssetsService.addAssetsLabel(assetsLabel);
        System.out.println(map);
    }

    @Test
    public void lb2() {
        AssetsLabel assetsLabel = new AssetsLabel();
        assetsLabel.setName("SDK-Name-Modify");
        assetsLabel.setValue("SDK-Name-Value");
        assetsLabel.setId("43ce643d-df31-4267-aadb-b9db3afec68b");
        Map<String, String> map = jmsAssetsService.updateAssetsLabel(assetsLabel);
        System.out.println(map);
    }
    @Test
    public void systemUserPush() {
        Map<String, String> map = jmsAssetsService.systemUserPush("43ce643d-df31-4267-aadb-b9db3afec68b");
        System.out.println(map);
    }



}
