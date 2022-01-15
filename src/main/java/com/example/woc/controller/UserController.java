package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //这是使用DES加密算法所需要的key——RainChain 2022.01.15
    private static final byte[] DES_KEY = { 1,41,113,-111,73,101,-91,-53 };

    //密码的加密DES算法——RainChain 2022.01.15)
    public static String encryptBasedDes(String password) {
        String encryptedPassword;
        try {
            //安全的随机数源 SecureRandom——RainChain 2022.01.15
            SecureRandom sr = new SecureRandom();
            //将需要的DES_KEY传入——RainChain 2022.01.15
            DESKeySpec deskey = new DESKeySpec(DES_KEY);

            //将deskey（DESKeySpec）转为SecretKeyFactory对象——RainChain 2022.01.15
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);

            // 进行加密——RainChain 2022.01.15
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);

            //使用Base64下的encodeToString转为字符串——RainChain 2022.01.15
            encryptedPassword = new Base64().encodeToString(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("密码加密失败！ERROR：", e);
        }
        return encryptedPassword;
    }

    //密码的解密——RainChain 2022.01.15
    public static String decryptBasedDes(String encryptedPassword) {
        String decryptedPassword;
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);

            decryptedPassword = new String(cipher.doFinal(new Base64().decode(encryptedPassword)));
        } catch (Exception e) {
            throw new RuntimeException("密码解密失败！ERROR:", e);
        }
        return decryptedPassword;
    }

    //这是一个示例,以POST方法提交数据
    @PostMapping("/simple")
    public void simple(String test) {
        //按住ctrl键来看看具体调用的这个函数吧
        userService.test(test);
    }

    /**
     * 完成注册功能
     * 选做：对密码进行加密处理
     * @param account
     */
    @PostMapping("/register")
    public void uploadUsername(Account account) {

        //同名判断——RainChain 2022.01.15
        if(userService.usernameCheck(account.getUsername())==1)
        {
            System.out.println("该用户名已经存在！");
            return ;
        }

        //密码加密——RainChain 2022.01.15
        String encryptedPassword=encryptBasedDes(account.getPassword());

        userService.signUp(new Account(account.getId(),account.getUsername(),encryptedPassword,account.getEmail()));

    }

    /**
     * 完成登录功能
     * @param account
     * @return 是否登录成功
     */
    @PostMapping("/login")
    public Boolean login(Account account) {

        //判断用户名是否存在——RainChain 2022.01.15
        if(userService.usernameCheck(account.getUsername())==0)
        {
            System.out.println("用户名不存在！");
            return false;
        }

        //解密后进行比较，如果相同返回true（登录成功）——RainChain 2022.01.15
        return account.getPassword().equals(decryptBasedDes(userService.login(account.getUsername()).getPassword()));
    }

}


