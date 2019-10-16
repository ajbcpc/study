package com.bjsxt.Controller;

import com.bjsxt.Service.RoleService;
import com.bjsxt.pojo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoleController {

    @Resource
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("page/showRole")
    public List<Role> showRole(){
        return roleService.showRole();
    }

    @ResponseBody
    @RequestMapping("page/update")
    public int update(Role role){
        return roleService.update(role);
    }

    @ResponseBody
    @RequestMapping("page/save")
    public int save(Role role){
        return roleService.insertOne(role);
    }

}
