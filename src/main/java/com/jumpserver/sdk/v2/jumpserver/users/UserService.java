package com.jumpserver.sdk.v2.jumpserver.users;

import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.model.User;
import com.jumpserver.sdk.v2.model.UserGroup;

import java.util.List;

public interface UserService {

    List<User> list();

    User get(String userId);

    List<User> getByName(String userName);

    User update(User user);

    User create(User user);

    ActionResponse delete(String userId);

    ActionResponse changePassword(String userId, String password);

    List<UserGroup> userGroups();

    UserGroup updateUserGroup(UserGroup usergroup);

    UserGroup createUserGroup(UserGroup usergroup);

    ActionResponse deleteUserGroup(String groupId);

}
