package com.hb.base.utils.image;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

  public static void copyStream(InputStream is, OutputStream os) {
    final int bufferSize = 1024;
    try {
      byte[] buffer = new byte[bufferSize];
      int len;
      while ((len = is.read(buffer, 0, bufferSize)) != -1) {
        os.write(buffer, 0, len);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String md5(String encTarget) {
    MessageDigest mdEnc = null;
    try {
      mdEnc = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      System.out.println("Exception while encrypting to md5");
      e.printStackTrace();
    } // Encryption algorithm
    mdEnc.update(encTarget.getBytes(), 0, encTarget.length());
    String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
    while (md5.length() < 32) {
      md5 = "0" + md5;
    }
    return md5;
  }
}
