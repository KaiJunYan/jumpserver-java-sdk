package com.jumpserver.sdk.v2.jumpserver.org;

import com.jumpserver.sdk.v2.common.ActionResponse;
import com.jumpserver.sdk.v2.model.Org;
import com.jumpserver.sdk.v2.model.OrgUsers;
import com.jumpserver.sdk.v2.model.User;

import java.util.List;

public interface OrgService {

    List<Org> listOrg();

    Org getOrg(String orgId);

    Org updateOrg(Org org);

    Org createOrg(Org org);

    ActionResponse deleteOrg(String orgId);

    List<OrgUsers> getOrgUsers(String orgId);

    OrgUsers createOrgUsers(OrgUsers orgusers);

    ActionResponse deleteOrgUsers(String orgId,String userId);

    List<OrgUsers> getOrgAdmins(String orgId);

    OrgUsers createOrgAdmins(OrgUsers orgusers);

    ActionResponse deleteOrgAdmins(String orgId,String userId);

}
