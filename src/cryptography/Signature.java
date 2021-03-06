package cryptography;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Base64;

public class Signature {

    private AsymDecryptPub asymDecryptPub;
    private MessageDigest digest;

    public Signature() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.asymDecryptPub = new AsymDecryptPub();
        this.digest = MessageDigest.getInstance("SHA-256");
    }

    //Verifies signature
    public boolean verify(String message, String digest, String publicKeyFilename) throws Exception {
        PublicKey publicKey = this.asymDecryptPub.getPublic(publicKeyFilename);
        String messageHash = buildHash(message);
        String digestHash = this.asymDecryptPub.decryptText(digest, publicKey);
        return messageHash.equals(digestHash);
    }

    private String buildHash(String message) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(this.digest.digest(message.getBytes("UTF-8")));
    }

}
