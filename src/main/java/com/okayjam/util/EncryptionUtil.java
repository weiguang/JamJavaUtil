package com.okayjam.util;

/**
 * Created by Weiguang Chen(chen2621978@gmail.com) on 2017/7/15 17:20.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

	
    private EncryptionUtil() {}

    /**
     * @Author Weiguang Chen(chen2621978@gmail.com) on 2017/7/15 17:21
     * @description MD5, length:32
     */
    public static String getMD5(String str) {
        MessageDigest digester;
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            if ((0xff & aHash) < 0x10) {
                hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
            } else {
                hexString.append(Integer.toHexString(0xFF & aHash));
            }
        }
        return hexString.toString();
    }

    /**
     * @Author Weiguang Chen(chen2621978@gmail.com) on 2017/7/15 17:21
     * @description SHA-256, length:64
     */
    public static String getSHA256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
