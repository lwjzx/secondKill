package com.jackliu.secondkill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Component;

/**
 * MD5的工具类，将字符串加密
 */
@Component
public class MD5Utils {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFromPass(String inputPass){
        String str = "" + salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String fromPassToDBPass(String fromPass, String salt){
        String str = "" + salt.charAt(0)+salt.charAt(2)+fromPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String salt){
        String fromPass = inputPassToFromPass(inputPass);
        return fromPassToDBPass(fromPass,salt);
    }

    public static void main(String[] args) {
        String pass = "123456";
        String fromPass = inputPassToFromPass(pass);
        System.out.println(fromPass);
        System.out.println(fromPassToDBPass(fromPass,"1a2b3c4d"));
        System.out.println(inputPassToDBPass(pass, "1a2b3c4d"));
    }
}
