package com.bjsxt.Service.impl;

import com.bjsxt.Service.PostService;
import com.bjsxt.mapper.PostMapper;
import com.bjsxt.pojo.Post;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper postMapper;

    public List<Post> showPost() {
        return postMapper.findAll();
    }
}
