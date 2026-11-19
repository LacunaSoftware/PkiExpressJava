package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Tests for PkiExpressOperator.escapeWindowsArgument, which protects pkie.exe on Windows from
 * argument injection (values containing quotes and backslashes being split into extra options).
 */
public class PkiExpressOperatorEscapingTest {

	@Test
	public void knownOutputs() {
		assertEquals("\"\"", PkiExpressOperator.escapeWindowsArgument(""));
		assertEquals("\"abc\"", PkiExpressOperator.escapeWindowsArgument("abc"));
		assertEquals("\"a b\"", PkiExpressOperator.escapeWindowsArgument("a b"));
		assertEquals("\"a\\\"b\"", PkiExpressOperator.escapeWindowsArgument("a\"b"));
		assertEquals("\"a\\\\\\\"b\"", PkiExpressOperator.escapeWindowsArgument("a\\\"b"));
		assertEquals("\"a\\b\"", PkiExpressOperator.escapeWindowsArgument("a\\b"));
		assertEquals("\"a\"\\\\\"\"", PkiExpressOperator.escapeWindowsArgument("a\\"));
		assertEquals("\"\"\\\\\"\"", PkiExpressOperator.escapeWindowsArgument("\\"));
		assertEquals("\"C:\\Program Files\"\\\\\"\"", PkiExpressOperator.escapeWindowsArgument("C:\\Program Files\\"));
	}

	@Test
	public void injectionPayloadsStayOneArgument() {
		String[] payloads = {
				"x\\\" --trust-test",
				"x\" --trust-test \"y",
				"x\\\\\" --trust-root C:\\evil.cer \"",
				"\"quoted\"",
				"\"q\\\"",
				"end\\\"",
				"\\\\server\\share\\",
		};
		for (String payload : payloads) {
			List<String> args = Arrays.asList("--reason", payload, "--base64");
			assertEquals(payload, args, roundTrip(args));
		}
	}

	/**
	 * Every string of up to 6 characters over an alphabet of the characters that matter to the
	 * Windows parser must come back unchanged, alone and between other arguments.
	 */
	@Test
	public void exhaustiveRoundTrip() {
		char[] alphabet = { 'a', '\\', '"', ' ', '\t' };
		List<String> values = new ArrayList<>();
		generate(alphabet, "", 6, values);
		for (String value : values) {
			String escaped = PkiExpressOperator.escapeWindowsArgument(value);
			assertPassedVerbatimByJdk(value, escaped);
			assertEquals(value, Arrays.asList(value), parseWindowsCommandLine(escaped));
			List<String> args = Arrays.asList("-a", value, value + "x", "\\" + value);
			assertEquals(value, args, roundTrip(args));
		}
	}

	private static void generate(char[] alphabet, String prefix, int maxLength, List<String> out) {
		out.add(prefix);
		if (prefix.length() < maxLength) {
			for (char c : alphabet) {
				generate(alphabet, prefix + c, maxLength, out);
			}
		}
	}

	/**
	 * Checks the conditions under which java.lang.ProcessImpl (Windows) considers an argument
	 * already quoted and copies it to the command line unchanged, on every JDK version.
	 */
	private static void assertPassedVerbatimByJdk(String value, String escaped) {
		assertTrue(value, escaped.length() >= 2);
		assertTrue(value, escaped.startsWith("\""));
		assertTrue(value, escaped.endsWith("\""));
		assertFalse(value, escaped.endsWith("\\\""));
	}

	/**
	 * Escapes the arguments and joins them the way the JDK does for already-quoted arguments,
	 * then splits the result again the way pkie.exe does.
	 */
	private static List<String> roundTrip(List<String> args) {
		StringBuilder cmdLine = new StringBuilder();
		for (String arg : args) {
			if (cmdLine.length() > 0) {
				cmdLine.append(' ');
			}
			cmdLine.append(PkiExpressOperator.escapeWindowsArgument(arg));
		}
		return parseWindowsCommandLine(cmdLine.toString());
	}

	/**
	 * Splits a command line using the rules shared by the C runtime and CommandLineToArgvW. Fails
	 * on "" inside a quoted section, where the two parsers disagree, so that the escaping is
	 * verified not to depend on it.
	 */
	private static List<String> parseWindowsCommandLine(String cmdLine) {
		List<String> args = new ArrayList<>();
		int i = 0;
		int n = cmdLine.length();
		while (true) {
			while (i < n && (cmdLine.charAt(i) == ' ' || cmdLine.charAt(i) == '\t')) {
				i++;
			}
			if (i >= n) {
				return args;
			}
			StringBuilder arg = new StringBuilder();
			boolean inQuotes = false;
			while (i < n) {
				char c = cmdLine.charAt(i);
				if (!inQuotes && (c == ' ' || c == '\t')) {
					break;
				}
				if (c == '\\') {
					int backslashes = 0;
					while (i < n && cmdLine.charAt(i) == '\\') {
						backslashes++;
						i++;
					}
					if (i < n && cmdLine.charAt(i) == '"') {
						appendBackslashes(arg, backslashes / 2);
						if (backslashes % 2 == 1) {
							arg.append('"');
							i++;
						}
						// With an even count the quote is handled by the next iteration.
					} else {
						appendBackslashes(arg, backslashes);
					}
				} else if (c == '"') {
					if (inQuotes && i + 1 < n && cmdLine.charAt(i + 1) == '"') {
						throw new AssertionError("Ambiguous \"\" inside a quoted section: " + cmdLine);
					}
					inQuotes = !inQuotes;
					i++;
				} else {
					arg.append(c);
					i++;
				}
			}
			args.add(arg.toString());
		}
	}

	private static void appendBackslashes(StringBuilder sb, int count) {
		for (int i = 0; i < count; i++) {
			sb.append('\\');
		}
	}
}
