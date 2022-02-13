package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.exception.OperationFailException;
import com.example.woc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 04:19
 **/
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    //请仿照 User 补充 Admin 的三层架构并完成接口
    @Autowired
    private UserService userService;

    /**
     * 获取当前的账户总数
     *
     * @return
     */
    @GetMapping("/getAmount")
    public Integer getAmountOfAccounts() {

        return userService.userCount();
    }

    /**
     * 根据用户名删除账户
     *
     * @param username
     */
    //超级管理员
    @DeleteMapping("/superAdmin/deleteAccount")
    public void deleteAccountForSuperAdmin(@NotBlank String username) {

        if (userService.getAccount(username).getRole() == 2) {
            throw new OperationFailException(403, "权限不足");
        }
        userService.deleteUser(username);

    }

    //普通管理员
    @DeleteMapping("/deleteAccount")
    public void deleteAccountForAdmin(@NotBlank String username) {
        int role = userService.getAccount(username).getRole();
        if (role == 1 || role == 2) {
            throw new OperationFailException(403, "权限不足");
        }
        userService.deleteUser(username);
    }

}
