package com.bjsxt.Service.impl;

import com.bjsxt.Service.MenuService;
import com.bjsxt.mapper.MenuMapper;
import com.bjsxt.pojo.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    public List<Menu> findRootMenu() {
        List<Menu> rootMenu = menuMapper.findAllRootMenu();
        for(Menu rootMenuItem : rootMenu){
            List<Menu> childrenMenu = menuMapper.findByPid(rootMenuItem.getId());
            rootMenuItem.setChildren(childrenMenu);
        }
        return rootMenu;
    }

}
