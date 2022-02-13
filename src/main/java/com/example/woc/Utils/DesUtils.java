package com.example.woc.Utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/12 20:20
 */
public class DesUtils {
    //这是使用DES加密算法所需要的key——RainChain 2022.01.15
    private static final byte[] DES_KEY = {1, 41, 113, -111, 73, 101, -91, -53};

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
}
