package com.example.webapi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

  public static byte[] getBytes(final InputStream is) throws IOException {
    int len;
    int size = 1024;
    byte[] buf;

    if (is instanceof ByteArrayInputStream) {
      size = is.available();
      buf = new byte[size];
      len = is.read(buf, 0, size);
    } else {
      final ByteArrayOutputStream bos = new ByteArrayOutputStream();
      buf = new byte[size];

      while ((len = is.read(buf, 0, size)) != -1) {
        bos.write(buf, 0, len);
      }

      buf = bos.toByteArray();
    }

    return buf;
  }
}
