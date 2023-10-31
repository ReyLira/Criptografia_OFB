package OFB;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AESOFBEncryptionExample {
    public static void main(String[] args) throws Exception {
        // Generar una clave AES
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        // Crear un vector de inicialización (IV)
        byte[] iv = new byte[16];
        // Puedes asignar valores específicos a cada byte del IV según tus necesidades
        iv[0] = 0x01;
        iv[1] = 0x02;
        // Y así sucesivamente para los 16 bytes del IV
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Datos a cifrar
        String plaintext = "Hola, mundo";

        // Inicialización del cifrador para el cifrado
        Cipher aesOFBEncrypt = Cipher.getInstance("AES/OFB/PKCS5Padding");
        aesOFBEncrypt.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        // Cifrado
        byte[] ciphertext = aesOFBEncrypt.doFinal(plaintext.getBytes());

        // Convertir el texto cifrado a Base64 para facilitar la impresión
        String ciphertextBase64 = Base64.getEncoder().encodeToString(ciphertext);
        System.out.println("Texto Cifrado (Base64): " + ciphertextBase64);

        // Inicialización del cifrador para el descifrado
        Cipher aesOFBDecrypt = Cipher.getInstance("AES/OFB/PKCS5Padding");
        aesOFBDecrypt.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        // Descifrado
        byte[] decryptedBytes = aesOFBDecrypt.doFinal(Base64.getDecoder().decode(ciphertextBase64));
        String decryptedText = new String(decryptedBytes);

        System.out.println("Texto Descifrado: " + decryptedText);
    }
}

