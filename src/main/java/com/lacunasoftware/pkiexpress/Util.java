package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {

    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.isEmpty());
    }

    public static byte[] readStream(InputStream stream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    public static byte[] decodeBase64(String s) {
        return new ObjectMapper().convertValue(s, byte[].class);
    }

    public static String encodeBase64(byte[] content) {
        return new ObjectMapper().convertValue(content, String.class);
    }
}
