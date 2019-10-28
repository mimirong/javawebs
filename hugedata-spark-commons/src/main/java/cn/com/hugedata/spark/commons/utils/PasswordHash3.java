package cn.com.hugedata.spark.commons.utils;

import java.io.UnsupportedEncodingException;

public final class PasswordHash3 {
    
    private static final String EXTRA = "PFZN-i9<RFXJ%Itzn,Q$M;tFFCaD;*[C]jmRY.hOW,YA*_B@mq<.3kj+I,&jrfdd";

    public static String hashPassword(String password) {
        try {
            String A = password + EXTRA;
            byte[] B = A.getBytes("UTF-8");
            return Md5Utils.hashToString(B);
            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    public static void main(String[] args) {
        System.out.println(hashPassword("1"));
    }
}
