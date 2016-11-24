package me.matoosh.zerok.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	//Hashes a string using the md5 algorithm.
    public static String stringToMd5(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("MD5");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
}
