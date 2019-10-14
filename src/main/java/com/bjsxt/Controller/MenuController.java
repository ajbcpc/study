package com.bjsxt.Controller;

import com.bjsxt.Service.MenuService;
import com.bjsxt.pojo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/page/showMenu")
    @ResponseBody
    public List<Menu> listMenu(){
        return menuService.findRootMenu();
    }
}
