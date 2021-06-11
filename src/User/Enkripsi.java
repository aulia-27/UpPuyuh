/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Aulia
 */
public class Enkripsi {
    
    private static String convertToHex(byte[] data) {
    StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                int halfbyte = (data[i] >>> 4) & 0x0F;
                int two_halfs = 0;
                do {                    
                    if ((0 <= halfbyte) && (halfbyte<=9)) {
                        buffer.append((char) ('0' + halfbyte));
                    } else {
                        buffer.append((char) ('a' + (halfbyte-10)));
                    }
                    halfbyte = data[i] & 0x0F;
                } while (two_halfs++<1);
            }
            return buffer.toString();
    }
    
    public static String getSHA1 (String text) throws NoSuchFieldException, UnsupportedOperationException, NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] shalhash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        shalhash = md.digest();
        return convertToHex(shalhash);
    }
    
}
