package com.bjsxt.mapper;

import com.bjsxt.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoleMapper {

    @Select("select * from x_role")
    public List<Role> selAll();

    @Update("update x_role set name=#{name}, sort=#{sort}, remark=#{remark} where id=#{id}")
    public int update(Role role);

    @Insert("insert into x_role (name, sort, remark) values(#{name}, #{sort}, #{remark})")
    public int insertOne(Role role);
}
