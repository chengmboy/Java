package com.chengmboy.util.common.security;

import java.security.*;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.chengmboy.util.common.crypto.exception.CryptoException;

/**
 * @author cheng_mboy
 */
public class RSAUtil {

    public static void main(String[] args) {
        process();
    }

    private static void process() {
        KeyPair pair = generateKeyPair(1024);
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        String data = "我是重要消息";
        System.out.println("发送方: " + data);
        System.out.println("私钥加密");
        byte[] encrypt = encrypt(data.getBytes(), privateKey);
        String s = Base64.getEncoder().encodeToString(encrypt);

        System.out.println("--------------------");
        System.out.println("传输：" + s);
        System.out.println("--------------------");

        byte[] decode = Base64.getDecoder().decode(s);
        byte[] decrypt = decrypt(decode, publicKey);
        String data1 = new String(decrypt);

        System.out.println("接收方: " + data1);

        System.out.println(data1.equals(data) ? "确定本人发送消息！" : "不是本人发送的消息！");
    }

    private static KeyPair generateKeyPair(int keysize) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(keysize);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    private static byte[] encrypt(byte[] data, Key key) {
        try {
            long s = System.nanoTime();
            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = rsa.doFinal(data);
            System.out.println("非对称加密花费时间： "+ (System.nanoTime()-s));
            return bytes;
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    private static byte[] decrypt(byte[] data, Key key) {
        try {
            long s = System.nanoTime();
            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.DECRYPT_MODE, key);
            byte[] bytes = rsa.doFinal(data);
            System.out.println("非对称解密花费时间： "+ (System.nanoTime()-s));
            return bytes;
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }
}