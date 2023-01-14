package com.demos.man;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;

public class ShiroMD5SaltTest {


    /**
     * 在数据库中不能用明文存储我们的密码，如果用明文储存我们的密码，那么数据库泄漏会产生安全问题
     * 通常会使用非对称加密，就是相当于不可逆的加密，md5符合，比如我们加密123456用md5加密后为
     * e10adc3949ba59abbe56e057f20f883e，无法通过计算还原回123456，这个加密字符串就可以
     * 保存在我们的数据库中，等用户下次登陆的时候我们把密码通过同样的算法与数据库中的密码进行对比，
     * 就能知道密码是否正确了，但是有一个问题就是如果用户密码相同，那么计算出来的MD5值就会相同，也不
     * 安全，所以我们可以采用md5+多次加盐来保证，就算别人得到我们密码的md5值，但是别人不会知道我们
     * 到底加密了多少次，所以很难破解
     */
    @Test
    public void testMD5Salt(){
        // 普通md5加密
        String password = "123456";
        String encodePassword = new Md5Hash(password).toString();
        System.out.println(encodePassword);
        // shiro加密
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int count = 3;// 加密次数
        String algorithmName = "md5";

        SimpleHash saltPassword = new SimpleHash(algorithmName, password, salt, count);
        System.out.println("加盐后的密码是: " + saltPassword);
    }
}
