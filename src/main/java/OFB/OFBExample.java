package OFB;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

import java.security.SecureRandom;

public class OFBExample {
    public static void main(String[] args) throws Exception {
        String originalText = "Este es un ejemplo de cifrado OFB en Java";
        SecretKey secretKey = generateAESKey();
        byte[] initializationVector = generateInitializationVector();

        byte[] encryptedText = encryptWithOFB(originalText, secretKey, initializationVector);
        String decryptedText = decryptWithOFB(encryptedText, secretKey, initializationVector);

        System.out.println("Texto Original: " + originalText);
        System.out.println("Texto Cifrado: " + new String(encryptedText));
        System.out.println("Texto Descifrado: " + decryptedText);
    }

    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Tamaño de clave de 128 bits
        return keyGenerator.generateKey();
    }

    private static byte[] generateInitializationVector() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    private static byte[] encryptWithOFB(String plaintext, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(plaintext.getBytes());
    }

    private static String decryptWithOFB(byte[] ciphertext, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes);
    }
}