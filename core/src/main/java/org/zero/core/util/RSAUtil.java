package org.zero.core.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangrui on 2017/10/10.
 */
public class RSAUtil {

    private static final String PRIVATE_KEY = "privateKey";
    private static final String PUBLIC_KEY = "publicKey";


    public static Map<String, byte[]> generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair kp = keyGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        Map<String, byte[]> map = new HashMap<>();
        byte[] privBytes = privateKey.getEncoded();
        byte[] pubBytes = publicKey.getEncoded();
        map.put(PRIVATE_KEY, privBytes);
        map.put(PUBLIC_KEY, pubBytes);
        return map;
    }

    public static byte[] encryptByPrivateKey(byte[] content, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
        Key privateKey = kf.generatePrivate(spec);
        Cipher cipher = Cipher.getInstance(kf.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    public static byte[] encryptByPublicKey(byte[] content, byte[] key) throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
        Key privateKey = kf.generatePublic(spec);
        Cipher cipher = Cipher.getInstance(kf.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    public static String decryptByPrivateKey(byte[] content, byte[] key) throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
        Key privateKey = kf.generatePrivate(spec);
        Cipher cipher = Cipher.getInstance(kf.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(content), "UTF-8");
    }

    public static String decryptByPublicKey(byte[] content, byte[] key) throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
        Key privateKey = kf.generatePublic(spec);
        Cipher cipher = Cipher.getInstance(kf.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(content), "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        Map<String, byte[]> map = generateKey();
        String content = "嘿鸭哦";
        byte[] encryptedByPrivateKey = encryptByPrivateKey(content.getBytes("UTF-8"), map.get(PRIVATE_KEY));
        String decryptedByPublicKey = decryptByPublicKey(encryptedByPrivateKey, map.get(PUBLIC_KEY));
        System.out.println(decryptedByPublicKey);

        byte[] encryptedByPublicKey = encryptByPublicKey(content.getBytes("UTF-8"), map.get(PUBLIC_KEY));
        String decrptedByPrivateKey = decryptByPrivateKey(encryptedByPublicKey, map.get(PRIVATE_KEY));
        System.out.println(decrptedByPrivateKey);
    }
}
