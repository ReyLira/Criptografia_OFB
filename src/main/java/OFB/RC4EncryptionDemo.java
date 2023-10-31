package OFB;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class RC4EncryptionDemo {
    public static void main(String[] args) throws Exception {
        // Clave secreta (debe ser compartida entre el remitente y el receptor)
        byte[] secretKey = "ClaveSecreta".getBytes();
        Key key = new SecretKeySpec(secretKey, "RC4");

        // Datos a cifrar
        byte[] plaintext = "Hola, mundo".getBytes();

        // Inicialización del cifrador RC4
        Cipher rc4 = Cipher.getInstance("RC4");
        rc4.init(Cipher.ENCRYPT_MODE, key);

        // Cifrado
        byte[] ciphertext = rc4.doFinal(plaintext);
        System.out.println("Texto Cifrado: " + new String(ciphertext));
    }
}

