package com.bjsxt.mapper;

import com.bjsxt.pojo.Post;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {

    @Select("select * from x_post")
    List<Post> findAll();
}
