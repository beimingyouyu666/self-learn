package com.yangk.selflearn.idempotent.byserialnumber;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
public class Md5Util {
    private Md5Util() {
        throw new IllegalStateException("Utility class");
    }

    public static String encodeBase64MD5String(String source) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        byte[] encode2bytes = encode2bytes(source);
        return Base64.encodeBase64String(encode2bytes);
    }

    public static byte[] encode2bytes(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(source.getBytes("UTF-8"));
        byte[] result = md.digest();
        return result;
    }

    public static String encode2hex(String source) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] data = encode2bytes(source);
        StringBuilder hexString = new StringBuilder();
        int len = data == null ? 0 : data.length;

        for (int i = 0; i < len; ++i) {
            String hex = Integer.toHexString(255 & data[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static boolean validate(String unknown, String okHex) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        return okHex.equals(encode2hex(unknown));
    }
}
