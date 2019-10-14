package com.bjsxt.Controller;

import com.bjsxt.Service.UserService;
import com.bjsxt.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public String login(User user, HttpSession session){
        User userLogin = userService.login(user);
        System.out.println("name="+user.getName()+", password="+user.getPassword());
        if(userLogin == null){
            return "0";
        }else{
            session.setAttribute("user", user);
            return "1";
        }

    }

}
