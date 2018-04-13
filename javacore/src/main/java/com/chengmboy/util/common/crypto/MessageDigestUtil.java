package com.chengmboy.util.common.crypto;

import java.security.*;

import com.chengmboy.util.common.crypto.exception.CryptoException;

/**
 * 该HashMessage类为应用程序提供消息摘要算法的功能，如SHA-1或SHA-256。
 * 消息摘要是采用任意大小的数据并输出固定长度散列值的安全单向散列函数。
 * <p>
 * Java平台的每个实现都需要支持以下标准的MessageDigest算法：
 * <p>
 * <ul>
 * <li>{@code MD5}</li>
 * <li>{@code SHA-1}</li>
 * <li>{@code SHA-256}</li>
 * </ul>
 *
 * @author cheng_mboy
 */
public class MessageDigestUtil {

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 用相应算法对数据进行摘要
     *
     * @param data      需要摘要的数据
     * @param algorithm 摘要算法
     * @return 摘要后的数据
     */
    public static byte[] digest(byte[] data, String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            return messageDigest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 用相应算法对数据进行摘要，并返回16进制字符串
     *
     * @param data      需要摘要的数据
     * @param algorithm 摘要算法
     * @return 摘要后的16进制字符串
     */
    public static String digest2HexString(byte[] data, String algorithm) {
        byte[] digest = digest(data, algorithm);
        return toHexString(digest);
    }

    public static byte[] md5Digest(byte[] data) {
        return digest(data, MD5);
    }

    public static String md5Digest2HexString(byte[] data) {
        return digest2HexString(data, MD5);
    }


    public static byte[] sha1Digest(byte[] data) {
        return digest(data, SHA1);
    }

    public static String sha1Digest2HexString(byte[] data) {
        return digest2HexString(data, SHA1);
    }

    public static byte[] sha256Digest(byte[] data) {
        return digest(data, SHA256);
    }

    public static String sha256Digest2HexString(byte[] data) {
        return digest2HexString(data, SHA256);
    }


    private static String toHexString(byte[] bytes) {
        StringBuilder r = new StringBuilder();
        for (byte b : bytes) {
            r.append(HEX_DIGITS[(b & 0xf0) >> 4])
                    .append(HEX_DIGITS[b & 0xf]);
        }
        return r.toString();
    }
}