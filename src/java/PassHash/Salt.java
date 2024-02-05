/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassHash;

/**
 *
 * @author sonbu
 */
import java.security.SecureRandom;
import java.util.Base64;

public class Salt {

    private static final SecureRandom random = new SecureRandom();
    private static final int SALT_LENGTH = 16; // Change this if you want a different salt length

    public static String generate() {
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
