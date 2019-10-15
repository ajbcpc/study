package com.bjsxt.Service.impl;

import com.bjsxt.Service.RoleService;
import com.bjsxt.mapper.RoleMapper;
import com.bjsxt.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    public List<Role> showRole() {
        return roleMapper.selAll();
    }

    public int update(Role role) {
        return roleMapper.update(role);
    }
}
