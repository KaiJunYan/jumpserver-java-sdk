# Jumpserver-java-sdk 

对Jumpserver提供的API进行封装，此为JAVA版本

```
  git checkout xpack 
```
切换分支，获取支持最新的接口，支持xpack插件的SDK版本


## Getting Started

```
<dependency>
    <groupId>com.fit2cloud</groupId>
    <artifactId>jumpserver-sdk</artifactId>
    <version>1.0</version>
</dependency>
```

examples: 
```
 JmsBaseService jmsBaseService =  new JmsBaseService(JMS_URL, JMS_USERNAME, JMS_PASSWORD);
 jmsUsersService = jmsBaseService.jmsUsersService();
 jmsUsersService.queryUser("id");
 jmsUsersService.addUser(new User());
 user user = new  User();
 user.setId("id");
 jmsUsersService.updateUser(user);
 jmsUsersService.deleteUser(user);
 
```
请看测试用例

### 编译

```
  mvn clean install 
```


### 上传
```
  mvn depoly
```

