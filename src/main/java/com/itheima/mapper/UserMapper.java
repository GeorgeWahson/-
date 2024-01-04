package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    /**
     * 根据用户名和密码查询用户对象
     * @param userName
     * @param password
     * @return
     */
    @Select("select * from tb_user where user_name = #{userName} and password = #{password}")
    User select(@Param("userName") String userName,@Param("password")  String password);

    /**
     * 根据用户名查询用户对象
     * @param userName
     * @return
     */
    @Select("select * from tb_user where user_name = #{userName}")
    User selectByUsername(String userName);

    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into tb_user values(null,#{userName},#{password})")
    void add(User user);
}
