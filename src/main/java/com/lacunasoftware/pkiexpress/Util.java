package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


class Util {

	// region String

	static String repeatChar(char c, int count) {
		char[] array = new char[count];
		Arrays.fill(array, c);
		return new String(array);
	}

	static String join(String delimiter, List<String> elements) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < elements.size(); i++) {
			sb.append(elements.get(i));
			if (i < elements.size() -1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	static boolean isNullOrEmpty(String s) {
		return s == null || s.length() == 0;
	}

	// endregion

	static byte[] readStream(InputStream stream) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = stream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		return buffer.toByteArray();
	}

	static byte[] decodeBase64(String s) {
		return new ObjectMapper().convertValue(s, byte[].class);
	}

	static String encodeBase64(byte[] content) {
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

	static Date parseApiDate(String dateStr) {
		Date date;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		try {
			date = fmt.parse(dateStr);
		} catch (ParseException ex) {
			date = null;
		}

		return date;
	}

	static void validateFile(String userFile, Path baseDir) {
		try {
			// get normalized path
			Path basePath = baseDir.toRealPath();
			Path userPath = basePath.resolve(userFile).normalize();

			// checks if user file path is child of base dir
			if (!userPath.startsWith(basePath)) {
				throw new RuntimeException("The provided file path is not valid");
			}

			// checks if file exists
			if (!Files.exists(userPath)) {
				throw new RuntimeException("The provided file was not found");
			}

		} catch (RuntimeException ex) {
			throw ex;

		} catch (Exception ex) {
			throw new RuntimeException("Error validating file path", ex);
		}
	}
}
