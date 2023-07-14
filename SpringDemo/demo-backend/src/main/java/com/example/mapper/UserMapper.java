package com.example.mapper;

import com.example.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // 根据用户名和邮箱查找指定账户，用户名和邮箱唯一且一一对应。
    @Select("SELECT * FROM db_account WHERE username = #{key} OR email = #{key}")
    Account getAccountByUsernameOrEmail(String key);

}
