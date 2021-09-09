package com.hb.base.utils;


import androidx.collection.SparseArrayCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TrimSign {

  private static TrimSign instance = null;

  public static synchronized TrimSign getInstances() {
    if (instance == null) {
      instance = new TrimSign();
    }
    return instance;
  }

  private static final SparseArrayCompat<String> SIGN_MAP = new SparseArrayCompat<>();

  static {
    SIGN_MAP.put(0x00e1, "a");
    SIGN_MAP.put(0x00E0, "a");
    SIGN_MAP.put(0x1EA3, "a");
    SIGN_MAP.put(0x00E3, "a");
    SIGN_MAP.put(0x1EA1, "a");
    SIGN_MAP.put(0x00E2, "a");
    SIGN_MAP.put(0x0103, "a");
    SIGN_MAP.put(0x1EAF, "a");
    SIGN_MAP.put(0x1EB1, "a");
    SIGN_MAP.put(0x1EB3, "a");
    SIGN_MAP.put(0x1EB5, "a");
    SIGN_MAP.put(0x1EB7, "a");
    SIGN_MAP.put(0x1EA5, "a");
    SIGN_MAP.put(0x1EA7, "a");
    SIGN_MAP.put(0x1EA9, "a");
    SIGN_MAP.put(0x1EAB, "a");
    SIGN_MAP.put(0x1EAD, "a");

    SIGN_MAP.put(0x00E9, "e");
    SIGN_MAP.put(0x00E8, "e");
    SIGN_MAP.put(0x1EBB, "e");
    SIGN_MAP.put(0x1EBD, "e");
    SIGN_MAP.put(0x1EB9, "e");
    SIGN_MAP.put(0x00EA, "e");
    SIGN_MAP.put(0x1EBF, "e");
    SIGN_MAP.put(0x1EC1, "e");
    SIGN_MAP.put(0x1EC3, "e");
    SIGN_MAP.put(0x1EC5, "e");
    SIGN_MAP.put(0x1EC7, "e");

    SIGN_MAP.put(0x00F3, "o");
    SIGN_MAP.put(0x00F2, "o");
    SIGN_MAP.put(0x1ECF, "o");
    SIGN_MAP.put(0x00F5, "o");
    SIGN_MAP.put(0x1ECD, "o");
    SIGN_MAP.put(0x00F4, "o");
    SIGN_MAP.put(0x1ED1, "o");
    SIGN_MAP.put(0x1ED3, "o");
    SIGN_MAP.put(0x1ED5, "o");
    SIGN_MAP.put(0x1ED7, "o");
    SIGN_MAP.put(0x1ED9, "o");
    SIGN_MAP.put(0x1EDB, "o");
    SIGN_MAP.put(0x1EDD, "o");
    SIGN_MAP.put(0x1EDF, "o");
    SIGN_MAP.put(0x1EE1, "o");
    SIGN_MAP.put(0x1EE3, "o");

    SIGN_MAP.put(0x00FA, "u");
    SIGN_MAP.put(0x00F9, "u");
    SIGN_MAP.put(0x1EE7, "u");
    SIGN_MAP.put(0x0169, "u");
    SIGN_MAP.put(0x1EE5, "u");
    SIGN_MAP.put(0x1EE9, "u");
    SIGN_MAP.put(0x1EEB, "u");
    SIGN_MAP.put(0x1EED, "u");
    SIGN_MAP.put(0x1EEF, "u");
    SIGN_MAP.put(0x1EF1, "u");

    SIGN_MAP.put(0x00FD, "y");
    SIGN_MAP.put(0x1EF3, "y");
    SIGN_MAP.put(0x1EF7, "y");
    SIGN_MAP.put(0x1EF9, "y");

    SIGN_MAP.put(0x00C1, "A");
    SIGN_MAP.put(0x00C0, "A");
    SIGN_MAP.put(0x1EA2, "A");
    SIGN_MAP.put(0x00C3, "A");
    SIGN_MAP.put(0x1EA0, "A");
    SIGN_MAP.put(0x00C2, "A");
    SIGN_MAP.put(0x0102, "A");
    SIGN_MAP.put(0x1EAE, "A");
    SIGN_MAP.put(0x1EB0, "A");
    SIGN_MAP.put(0x1EB2, "A");
    SIGN_MAP.put(0x1EB4, "A");
    SIGN_MAP.put(0x1EB6, "A");
    SIGN_MAP.put(0x1EA4, "A");
    SIGN_MAP.put(0x1EA6, "A");
    SIGN_MAP.put(0x1EA8, "A");
    SIGN_MAP.put(0x1EAA, "A");
    SIGN_MAP.put(0x1EAC, "A");

    SIGN_MAP.put(0x00C9, "E");
    SIGN_MAP.put(0x00C8, "E");
    SIGN_MAP.put(0x1EBA, "E");
    SIGN_MAP.put(0x1EBC, "E");
    SIGN_MAP.put(0x1EB8, "E");
    SIGN_MAP.put(0x00CA, "E");
    SIGN_MAP.put(0x1EBE, "E");
    SIGN_MAP.put(0x1EC0, "E");
    SIGN_MAP.put(0x1EC2, "E");
    SIGN_MAP.put(0x1EC4, "E");
    SIGN_MAP.put(0x1EC6, "E");

    SIGN_MAP.put(0x00D3, "O");
    SIGN_MAP.put(0x00D2, "O");
    SIGN_MAP.put(0x1ECE, "O");
    SIGN_MAP.put(0x00D5, "O");
    SIGN_MAP.put(0x1ECC, "O");
    SIGN_MAP.put(0x00D4, "O");
    SIGN_MAP.put(0x1ED0, "O");
    SIGN_MAP.put(0x1ED2, "O");
    SIGN_MAP.put(0x1ED4, "O");
    SIGN_MAP.put(0x1ED6, "O");
    SIGN_MAP.put(0x1ED8, "O");
    SIGN_MAP.put(0x1EDA, "O");
    SIGN_MAP.put(0x1EDC, "O");
    SIGN_MAP.put(0x1EDE, "O");
    SIGN_MAP.put(0x1EE0, "O");
    SIGN_MAP.put(0x1EE2, "O");

    SIGN_MAP.put(0x00DA, "U");
    SIGN_MAP.put(0x00D9, "U");
    SIGN_MAP.put(0x1EE6, "U");
    SIGN_MAP.put(0x0168, "U");
    SIGN_MAP.put(0x1EE4, "U");
    SIGN_MAP.put(0x1EE8, "U");
    SIGN_MAP.put(0x1EEA, "U");
    SIGN_MAP.put(0x1EEC, "U");
    SIGN_MAP.put(0x1EEE, "U");
    SIGN_MAP.put(0x1EF0, "U");

    SIGN_MAP.put(0x00DD, "Y");
    SIGN_MAP.put(0x1EF2, "Y");
    SIGN_MAP.put(0x1EF6, "Y");
    SIGN_MAP.put(0x1EF8, "Y");
    SIGN_MAP.put(0x1EF5, "Y");

    SIGN_MAP.put(0x0111, "d");

    SIGN_MAP.put(0x00ED, "i");
    SIGN_MAP.put(0x00EC, "i");
    SIGN_MAP.put(0x1EC9, "i");
    SIGN_MAP.put(0x0129, "i");
    SIGN_MAP.put(0x1ECB, "i");

    SIGN_MAP.put(0x01A1, "o");
    SIGN_MAP.put(0x01B0, "u");

    SIGN_MAP.put(0x0110, "D");

    SIGN_MAP.put(0x00CD, "I");
    SIGN_MAP.put(0x00CC, "I");
    SIGN_MAP.put(0x1EC8, "I");
    SIGN_MAP.put(0x0128, "I");
    SIGN_MAP.put(0x1ECA, "I");

    SIGN_MAP.put(0x01A0, "O");

    SIGN_MAP.put(0x01AF, "U");
  }


  private TrimSign() {
  }

  public String unicodeTrimSign(final String str) {
    int length = str.length();
    String out = "";
    for (int i = 0; i < length; i++) {

      char cTemp = str.charAt(i);
      String cUniHex = String.format("0x%s", Integer.toHexString(cTemp));

      int key = Integer.decode(cUniHex);
      String c = SIGN_MAP.get(key);
      if (c != null) {
        out += String.valueOf(c);
      } else {
        out += String.valueOf(cTemp);
      }
    }
    return out;
  }

  public static String convertStreamToString(InputStream is) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();

    String line;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return sb.toString();
  }

}
