package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //示例
    public void test(String test) {
        userMapper.test(test);
    }

    //username同名判断——RainChain 2022.01.15
    public int usernameCheck(String username)
    {
        return userMapper.usernameCheck(username);
    }

    //sign up——RainChain 2022.01.15
    public void signUp(Account account)
    {
        userMapper.signUp(account);
    }

    //login——RainChain 2022.01.15
    public Account login(String username)
    {
        return userMapper.encryptedPasswordGetter(username);
    }

    //admin 统计账号数——RainChain 2022.01.15
    public int userCount()
    {
        return userMapper.userCount();
    }

    //admin 删除指定账号——RainChain 2022.01.15
    public void deleteUser(String username)
    {
        userMapper.deleteUser(username);
    }
}
