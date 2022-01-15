package com.example.woc.controller;

import com.example.woc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 04:19
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    //请仿照 User 补充 Admin 的三层架构并完成接口
    @Autowired
    private UserService userService;
    /**
     * 获取当前的账户总数
     * @return
     */
    @GetMapping("/getAmount")
    public Integer getAmountOfAccounts(){

        return userService.userCount();
    }

    /**
     * 根据用户名删除账户
     * @param username
     */
    @PutMapping("deleteAccount")
    public void deleteAccount(String username){

        userService.deleteUser(username);

    }
}
