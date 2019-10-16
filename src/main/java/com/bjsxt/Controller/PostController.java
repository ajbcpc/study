package com.bjsxt.Controller;

import com.bjsxt.Service.PostService;
import com.bjsxt.pojo.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PostController {

    @Resource
    private PostService postService;

    @ResponseBody
    @RequestMapping("page/showPost")
    public List<Post> showPost(){
        return postService.showPost();
    }
}
