# Jumpserver-java-sdk  v2.0

对Jumpserver提供的API进行封装，此为支持xpack插件的SDK版本，适配Jumpserver V1.5.3以后的版本

## Getting Started

```
<dependency>
    <groupId>com.fit2cloud</groupId>
    <artifactId>jumpserver-sdk</artifactId>
    <version>2.0-153-SNAPSHOT</version>
</dependency>
```

### examples: 
``` 
  JMSClient os = new ClientBuilder()
                   ClientBuilder credentials = new ClientBuilder()
                  .endpoint("endPoint")
                  .credentials("keyId", "keySecret")
                  .header("x-jms-org", "组织ID")
                  .authenticate();

  
  //列出所有用户
  List<User> users = os.users().list();
  
  //搜索用户
  List<User> users = os.users().search("user01");
  
  //创建用户
  User user = new User();
  User result = os.users().create(user);
  
  //更新用户
  User user = new User();
  user.setId("id");
  User result = os.users().update(user);
  
  //删除用户
  os.users().delete("id");
  
```
以上为携带name为x-jms-org，value为组织ID的请求头，对相应组织进行操作，请看测试用例


```
 JMSClient os = new ClientBuilder()
                  .endpoint("endPoint")
                  .credentials("keyId", "keySecret")
                  .authenticate();
```
以上为不需要携带x-jms-org的请求头，对Default组织进行操作，请看测试用例


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
