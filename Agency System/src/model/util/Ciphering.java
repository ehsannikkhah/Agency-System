package model.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ehsan on 8/29/2018.
 */
public class Ciphering {

    public String mD5(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("md5");
        digest.update(s.getBytes(),0,s.length());
        String md5 = new BigInteger(1, digest.digest()).toString(16);
        return md5;
    }
}
