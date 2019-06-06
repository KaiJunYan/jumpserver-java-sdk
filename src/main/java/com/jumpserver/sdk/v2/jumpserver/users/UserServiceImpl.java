package com.jumpserver.sdk.v2.jumpserver.users;

import com.alibaba.fastjson.JSON;
import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.common.BaseJMSService;
import com.jumpserver.sdk.v2.common.ClientConstants;
import com.jumpserver.sdk.v2.model.entity.MapEntity;
import com.jumpserver.sdk.v2.model.User;
import com.jumpserver.sdk.v2.model.UserGroup;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserServiceImpl extends BaseJMSService implements UserService {

    @Override
    public List<User> list() {
        return get(User.class, uri(ClientConstants.USERS)).executeList();
    }

    @Override
    public User get(String userId) {
        checkNotNull(userId);
        return get(User.class, ClientConstants.USERS, userId).execute();
    }

    @Override
    public List<User> getByName(String userName) {
        checkNotNull(userName);
        return get(User.class, uri(ClientConstants.USERS)).param("name", userName).executeList();
    }

    @Override
    public ActionResponse delete(String userId) {
        checkNotNull(userId);
        return deleteWithResponse(ClientConstants.USERS, userId, "/").execute();
    }

    @Override
    public User update(User user) {
        checkNotNull(user);
        return patch(User.class, ClientConstants.USERS, user.getId(), "/").json(JSON.toJSONString(user)).execute();
    }

    @Override
    public User create(User user) {
        checkNotNull(user);
        return post(User.class, uri(ClientConstants.USERS))
                .json(JSON.toJSONString(user))  // .entity(user)
                .execute();
    }

    @Override
    public ActionResponse changePassword(String userId, String password) {
        checkNotNull(userId);
        checkNotNull(password);
        MapEntity mapEntity = MapEntity.create("password", password);
        String url = ClientConstants.USER_PASSWORD_RESET.replace("{id}", userId);
        return put(ActionResponse.class, url).entity(mapEntity).execute();
    }

    @Override
    public List<UserGroup> userGroups() {
        return get(UserGroup.class, uri(ClientConstants.USERGROUPS)).executeList();
    }

    @Override
    public UserGroup createUserGroup(UserGroup usergroup) {
        checkNotNull(usergroup);
        return post(UserGroup.class, uri(ClientConstants.USERGROUPS))
                .json(JSON.toJSONString(usergroup))
                .execute();
    }

    @Override
    public ActionResponse deleteUserGroup(String groupId) {
        checkNotNull(groupId);
        return deleteWithResponse(ClientConstants.USERGROUPS, groupId, "/").execute();
    }

    @Override
    public UserGroup updateUserGroup(UserGroup usergroup) {
        checkNotNull(usergroup);
        return patch(UserGroup.class, uri(ClientConstants.USERGROUPS), usergroup.getId(), "/")
                .json(JSON.toJSONString(usergroup))
                .execute();
    }

}
