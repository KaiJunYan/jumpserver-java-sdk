# Jumpserver-java-sdk  v2.0

对Jumpserver提供的API进行封装，此为支持xpack插件的SDK版本

## Getting Started

```
<dependency>
    <groupId>com.fit2cloud</groupId>
    <artifactId>jumpserver-sdk</artifactId>
    <version>2.0</version>
</dependency>
```

- xpack examples: 
``` 
  JMSClient os = new ClientBuilder()
                  .endpoint("http://localhost:8088")
                  .credentials("admin", "admin")
                  .header("x-jms-org", "7c6955bc-f004-4573-b46a-4072b0dd0d03")
                  .authenticate();
  System.out.println(os.getToken().getToken());
  
  List<User> users = os.users().list();
  
  List<User> users = os.users().getByName("user01");
  
  User user = new User();
  User result = os.users().create(user);
  
  User user = new User();
  user.setId("id");
  User result = os.users().update(user);
  
  os.users().delete("id");
  
```
携带name为x-jms-org，value为组织Id的请求头，请看测试用例


- without xpack examples:
```
 JMSClient os = new ClientBuilder()
                  .endpoint("http://localhost:8088")
                  .credentials("admin", "admin")
                  .authenticate();
  System.out.println(os.getToken().getToken());
```
不需要携带请求头，单一组织，请看测试用例


### 编译

```
  //先执行测试
  mvn clean install 
  
  //skip test
  mvn clean install -Dmavn.test.skip=true
  
  //单或者多个test测试
  mvn test -Dtest=JMSClientTest
  mvn test -Dtest=JMSClientTest,JmsUserServiceTest
  
  
```


### 上传
```
  mvn depoly
```
需要先修改pom文件的 distributionManagement 标签的值
