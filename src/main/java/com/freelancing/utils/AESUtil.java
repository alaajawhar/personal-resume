package com.freelancing.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;

/**
 * @author Alaa Jawhar
 */
@UtilityClass
@Slf4j
public class AESUtil {

    /*
    * Resource: https://github.com/mpetersen/aes-example/blob/master/README.md
    */

    private static String passPhrase = "passPhrase";
    private static String salt = "73616c74";
    private static String IvHexEncoded = "00000000000000000000000000000000";

    private static SecretKey key = getKeyFromPassword(passPhrase, salt);
    private static IvParameterSpec ivParameterSpec = getIvFromString(IvHexEncoded);

    public static String encrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static Optional<String> decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(cipherText));
            return Optional.of(new String(plainText));
        } catch (Exception e) {
            log.error("", e);
            return Optional.empty();
        }
    }

    private static SecretKey getKeyFromPassword(String passphrase, String salt) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), 100, 128);
            SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
            return key;
        } catch (Exception e) {
            log.error("", e);
            throw new RuntimeException("Exception caught, bye");
        }
    }

    private static byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }
    /*
     * IV is a pseudo-random value and has the same size as the block that is encrypted
     */
    private static IvParameterSpec getIvFromString(String ivAsString) {

        return new IvParameterSpec(hex(ivAsString));

//        byte[] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        return new IvParameterSpec(iv);
    }


    public static void main(String[] args) throws Exception {

        String input = "alaa";
        String cipherText = AESUtil.encrypt(input);
        System.out.println("cipherText: " + cipherText);
        Optional<String> plainText = AESUtil.decrypt(cipherText);

        if (plainText.isPresent() == Boolean.FALSE){
            log.error("Decryption failed");
        }

        System.out.println("plainText: " + plainText.get());
    }




}
