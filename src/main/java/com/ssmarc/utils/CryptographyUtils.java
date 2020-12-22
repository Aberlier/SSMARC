package com.ssmarc.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/22 9:28
 * @description：加密工具（主要是shiro的MD5）
 * @modified By：
 * @version: v1.0.0$
 */
public class CryptographyUtils {

        /**
         * title:CryptographyUtil.java
         * description:Md5加密
         * time:2017年1月16日 下午10:55:20
         * author:debug-steadyjack
         * @param str 密码
         * @param salt 盐
         * @return
         */
        public static String md5(String str,String salt){
            return new Md5Hash(str,salt).toString();
        }

        public static void main(String[] args) {
            String password="linsen";

            System.out.println("Md5加密："+ com.ssmarc.utils.CryptographyUtils.md5(password, "debug"));
        }

}
