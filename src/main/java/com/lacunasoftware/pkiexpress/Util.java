package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Util {

    public static String repeatChar(char c, int count) {
        char[] array = new char[count];
        Arrays.fill(array, c);
        return new String(array);
    }

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

    static ColorModel convertColorToModel(Color color) {
        ColorModel model = new ColorModel();
        model.setAlpha(color.getAlpha() / 2.55); // alpha in Rest PKI is 0-100
        model.setRed(color.getRed());
        model.setGreen(color.getGreen());
        model.setBlue(color.getBlue());
        return model;
    }
}
