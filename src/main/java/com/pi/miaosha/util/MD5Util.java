package com.pi.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 12:21
 **/
public class MD5Util {

    private static final String salt ="root";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }


    /**
     * 用户输入密码+salt MD5加密
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass){
       String str = inputPass+salt;
       return md5(str);
    }

    /**
     * 加密后的MD5+数据库salt 再次MD5加密
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDbPass(String formPass,String salt){
        String str = formPass+salt;
        return md5(str);
    }



    public static String inputPassToDbPass(String input,String saltDB){
         String formPass = inputPassToFormPass(input);
         String dbPass =formPassToDbPass(formPass,saltDB);
         return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(
                inputPassToDbPass("123456","root")
        );
        System.out.println(formPassToDbPass("123456",salt));
    }
}
