package com.lacunasoftware.pkiexpress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SignerTest {

	private static class TestSigner extends Signer {
		TestSigner() throws IOException {
			super(new PkiExpressConfig());
		}

		List<String> buildArgs() {
			List<String> args = new ArrayList<String>();
			verifyAndAddCommonOptions(args);
			return args;
		}

		String getMinVersion() {
			return versionManager.getMinVersion().toString();
		}
	}

	private Path certFile;

	@Before
	public void setUp() throws IOException {
		certFile = Files.createTempFile("pkie-signer-test", ".cer");
	}

	@After
	public void tearDown() throws IOException {
		Files.deleteIfExists(certFile);
	}

	@Test
	public void thumbprint() throws IOException {
		TestSigner signer = new TestSigner();
		signer.setCertificateThumbprint("abc");
		assertEquals(Arrays.asList("--thumbprint", "abc"), signer.buildArgs());
	}

	@Test
	public void pkcs11KeyIdWithPassword() throws IOException {
		TestSigner signer = new TestSigner();
		signer.setKeyId("my-key-label");
		signer.setCertPassword("1234");
		assertEquals(Arrays.asList("--password", "1234", "--key-id", "my-key-label"), signer.buildArgs());
		assertEquals("1.37", signer.getMinVersion());
	}

	@Test
	public void azureKeyName() throws IOException {
		TestSigner signer = new TestSigner();
		signer.setAzureKeyName("my-azure-key");
		signer.setCertificateFile(certFile);
		assertEquals(Arrays.asList("--key-name", "my-azure-key", "--cert-file", certFile.toString()), signer.buildArgs());
		assertEquals("1.20", signer.getMinVersion());
	}

	@Test
	public void kmsKeyId() throws IOException {
		TestSigner signer = new TestSigner();
		signer.setKmsKeyId("alias/my-key");
		signer.setCertificateFile(certFile.toString());
		assertEquals(Arrays.asList("--kms-key-id", "alias/my-key", "--cert-file", certFile.toString()), signer.buildArgs());
		assertEquals("1.39", signer.getMinVersion());
	}

	@Test
	public void noKeySourceThrows() throws IOException {
		TestSigner signer = new TestSigner();
		try {
			signer.buildArgs();
			fail("Expected RuntimeException");
		} catch (RuntimeException e) {
			assertTrue(e.getMessage().contains("Azure Key Vault key name or AWS KMS key id was provided"));
		}
	}

	@Test
	public void azureKeyNameWithoutCertificateFileThrows() throws IOException {
		TestSigner signer = new TestSigner();
		signer.setAzureKeyName("my-azure-key");
		try {
			signer.buildArgs();
			fail("Expected RuntimeException");
		} catch (RuntimeException e) {
			assertTrue(e.getMessage().contains("certificate file must be provided"));
		}
	}

	@Test
	public void kmsKeyIdWithoutCertificateFileThrows() throws IOException {
		TestSigner signer = new TestSigner();
		signer.setKmsKeyId("arn:aws:kms:us-east-1:111122223333:key/1234abcd-12ab-34cd-56ef-1234567890ab");
		try {
			signer.buildArgs();
			fail("Expected RuntimeException");
		} catch (RuntimeException e) {
			assertTrue(e.getMessage().contains("certificate file must be provided"));
		}
	}

	@Test(expected = FileNotFoundException.class)
	public void missingCertificateFileThrows() throws IOException {
		TestSigner signer = new TestSigner();
		signer.setCertificateFile(certFile.resolveSibling("does-not-exist-" + System.nanoTime() + ".cer"));
	}
}
