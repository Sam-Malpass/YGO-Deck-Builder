/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PasswordAuthenticator {
    /**
     * ID is the prefix for all produced tokens
     */
    public static final String ID = "$31$";
    /**
     * DEFAULT_COST holds the minimum recommended cost
     */
    public static final int DEFAULT_COST = 16;
    /**
     * ALGORITHM holds the name of the algorithm to use
     */
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    /**
     * SIZE is used to determine the size of the hash
     */
    private static final int SIZE = 128;
    /**
     * layout defines the pattern that hashed passwords should follow
     */
    private static final Pattern layout = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");
    /**
     * random is used to generate cryptographically secure random objects
     */
    private final SecureRandom random;
    /**
     * cost is used to hold the cost that is supplied
     */
    private final int cost;
    /**
     * Constructor with no arguments
     * <p>
     *     Calls the constructor with the DEFAULT_COST as the argument
     * </p>
     */
    public PasswordAuthenticator() {
        /*Call the constructor using an argument*/
        this(DEFAULT_COST);
    }
    /**
     * Constructor with argument
     * <p>
     *     Checks that the cost is an appropriate number then
     *     sets up the cost and random object accordingly
     * </p>
     * @param cost is the passed value to use at the cost
     */
    public PasswordAuthenticator(int cost) {
        /*Check that the cost is appropriate*/
        iterations(cost);
        /*Set the cost to the passed value*/
        this.cost = cost;
        /*Create a new SecureRandom object*/
        this.random = new SecureRandom();
    }
    /**
     * Function definition for iterations()
     * <p>
     *     Checks that the cost is within the appropriate bounds
     * </p>
     * @param cost is the number to be checked
     * @return a number
     */
    private static int iterations(int cost) {
        /*If the cost is less than zero or greater than thirty*/
        if ((cost < 0) || (cost > 30))
            /*Throw an exception*/
            throw new IllegalArgumentException("cost: " + cost);
        /*Return the number*/
        return 1 << cost;
    }
    /**
     * Function definition for hash()
     * <p>
     *     Salts and hashes a passed character array
     * </p>
     * @param password is the password to be hashed
     * @return the hashed password with the identifier at the start and the cost for un-hashing later
     */
    public String hash(char[] password) {
        /*Create an empty byte array*/
        byte[] salt = new byte[SIZE / 8];
        /*Generate the bytes*/
        random.nextBytes(salt);
        /*Create an empty byte array*/
        byte[] dk = pbkdf2(password, salt, 1 << cost);
        /*Create an empty byte array*/
        byte[] hash = new byte[salt.length + dk.length];
        /*Copy the salt array into hash array*/
        System.arraycopy(salt, 0, hash, 0, salt.length);
        /*Copy the dk array into hash array*/
        System.arraycopy(dk, 0, hash, salt.length, dk.length);
        /*Create a Base64.Encoder object*/
        Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
        /*Return an encoded hashed password*/
        return ID + cost + '$' + enc.encodeToString(hash);
    }
    /**
     * Function definition for authenticate()
     * <P>
     *     Checks that the token matches the layout pattern and then works on decoding and
     *     converting the hashed password to the base string
     * </P>
     * @param password is the input password to check
     * @param token is the hashed password to test against
     * @return whether the password is a match
     */
    public boolean authenticate(char[] password, String token) {
        /*Creates a Matcher object using the token*/
        Matcher m = layout.matcher(token);
        /*If it doesn't match*/
        if (!m.matches())
            /*Throw an exception*/
            throw new IllegalArgumentException("Invalid token format");
        /*Get the iterations from the matcher*/
        int iterations = iterations(Integer.parseInt(m.group(1)));
        /*Get the hash from the matcher*/
        byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
        /*Get the salt from the hash*/
        byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
        /*Create a byte array using the password, salt and iterations*/
        byte[] check = pbkdf2(password, salt, iterations);
        /*Create an integer*/
        int zero = 0;
        /*For all the elements in the array*/
        for (int idx = 0; idx < check.length; ++idx)
            /*Modify zero if there is discrepancy between the hash and the check*/
            zero |= hash[salt.length + idx] ^ check[idx];
        /*Return the boolean result*/
        return zero == 0;
    }
    /**
     * Function definition for pbkdf2()
     * <p>
     *     Generates a secret key based off of a key spec
     * </p>
     * @param password is the password to use
     * @param salt is the salt to use
     * @param iterations is the number of times to iterate
     * @return the byte[] key
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
            return f.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
        }
        catch (InvalidKeySpecException ex) {
            throw new IllegalStateException("Invalid SecretKeyFactory", ex);
        }
    }
    /**
     * Function definition for hash()
     * <p>
     *     This function serves as pre-processing if a string is passed to the hash
     *     function
     * </p>
     * @param password is the password to be hashed
     * @return the result of the hash function after pre-processing has occurred on the string
     */
    @Deprecated
    public String hash(String password) {
        /*Return the result of the hash on the password processed to the char array*/
        return hash(password.toCharArray());
    }
    /**
     * Function definition for authenticate()
     * <p>
     *     This function serves as pre-processing if a string is passed to the
     *     authenticate function
     * </p>
     * @param password is the input password to be checked
     * @param token is the token to test against
     * @return the result of the authenticate function after pre-processing has occurred on the string
     */
    @Deprecated
    public boolean authenticate(String password, String token) {
        /*Return the result of the authenticate on the password processed to the char array*/
        return authenticate(password.toCharArray(), token);
    }
}
