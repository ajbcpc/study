package com.bjsxt.Service;

import com.bjsxt.pojo.Role;

import java.util.List;

public interface RoleService {

    public List<Role> showRole();

    public int update(Role role);
}
