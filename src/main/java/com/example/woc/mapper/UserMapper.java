package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Mapper
@Repository
public interface UserMapper {
    //示例
    void test(@Param("value") String test);

    void signUp(Account account);

    int usernameCheck(@Param("username") String username);

    //按照代码逻辑，只可能存在一个匹配的账户，所以返回值直接设为Account——RainChain 2022.01.15
    Account getAccount(@Param("username") String username);

    int userCount();

    void deleteUser(@Param("username") String username);
}
