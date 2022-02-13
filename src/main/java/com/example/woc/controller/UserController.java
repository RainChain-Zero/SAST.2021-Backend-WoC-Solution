package com.example.woc.controller;

import com.example.woc.Utils.DesUtils;
import com.example.woc.Utils.JWTUtils;
import com.example.woc.component.ValidGroup;
import com.example.woc.entity.Account;
import com.example.woc.exception.OperationFailException;
import com.example.woc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 完成注册功能
     * 选做：对密码进行加密处理
     * @param account
     */
    @PostMapping("/register")
    public void uploadUsername(@Validated(value = {ValidGroup.usernameCheck.class,
    ValidGroup.passwordCheck.class,ValidGroup.emailCheck.class}) Account account) {

        //同名判断——RainChain 2022.01.15
        if(userService.usernameCheck(account.getUsername())==1)
        {
            throw new OperationFailException(403,"已有相同用户名");
        }

        //密码加密——RainChain 2022.01.15
        String encryptedPassword=DesUtils.encryptBasedDes(account.getPassword());

        userService.signUp(new Account(account.getUsername(),
                encryptedPassword,account.getEmail(),
                account.getRole()));
    }

    /**
     * 完成登录功能
     * @param account
     * @return 是否登录成功
     */
    @PostMapping("/login")
    public String login(@Validated(value = {ValidGroup.usernameCheck.class,
    ValidGroup.passwordCheck.class}) Account account) {

        //判断用户名是否存在——RainChain 2022.01.15
        if(userService.usernameCheck(account.getUsername())==0)
        {
            throw new OperationFailException(401,"用户名不存在");
        }
        //将输入的密码和数据库中对应的解密后的密码进行比较
        Account account1=userService.getAccount(account.getUsername());
        if(account.getPassword().equals(DesUtils.decryptBasedDes(account1.getPassword())))
        {
            //每次重新登录的时候新建新的token
            //Map中存payload
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("username",account1.getUsername());
            map.put("email",account1.getEmail());
            map.put("role",account1.getRole());
            //生成token
            return JWTUtils.getToken(map);
        }
        throw new OperationFailException(403,"密码错误");
    }
}


