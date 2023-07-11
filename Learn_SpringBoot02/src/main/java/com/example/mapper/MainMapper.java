package com.example.mapper;


import com.example.entity.UserData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MainMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    UserData findUserByName(String username);
}
