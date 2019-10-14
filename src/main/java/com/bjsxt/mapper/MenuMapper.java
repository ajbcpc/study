package com.bjsxt.mapper;

import com.bjsxt.pojo.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {

    @Select("select * from x_menu where pid = 0")
    public List<Menu> findAllRootMenu();

    @Select("select * from x_menu where pid = #{pid}")
    public List<Menu> findByPid(int pid);
}
