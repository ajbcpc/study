package com.bjsxt.mapper;

import com.bjsxt.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from x_user where name=#{name} and password=#{password}")
    User selBuyUser(User user);
}
