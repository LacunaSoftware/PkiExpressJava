package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for CadesSigner.
 * Tests the sign() method which contains an invoke() call.
 * Also includes a complete signature flow test using CadesSignatureStarter,
 * HashSigner, and SignatureFinisher.
 */
public class CadesSignerTest {

    private CadesSigner signer;

    @Before
    public void setUp() throws IOException {
        signer = new CadesSigner();
    }

    @Test
    public void testSign_WithInvokeCall() throws IOException {
        // This test exercises sign(boolean getCert) which calls
        // invoke(CommandEnum.CommandSignCades, args)
        // Note: This requires a valid file and certificate/key to work properly

        Path outputFile = Files.createTempFile("test-output", ".p7s");
        try {
            signer.setFileToSign(TestUtils.LoadSamplePdf());
            signer.setOutputFile(outputFile);
            signer.setPkcs12(TestUtils.LoadSamplePkcs12AsPath());
            signer.setCertPassword(TestUtils.getSampleCertificatePassword());
            signer.setTrustLacunaTestRoot(true); // added test root for lacuna so we can test with test certificates
            PKCertificate certificate = signer.sign(true);

            assertTrue("Signer certificate thumbprint should not be null", certificate.getThumbprint() != null);
            assertTrue("Signer certificate subject name should not be null", certificate.getSubjectName() != null);
            assertTrue("Signer certificate issuer name should not be null", certificate.getIssuerName() != null);
            assertTrue("Signer certificate serial number should not be null", certificate.getSerialNumber() != null);
            assertTrue("Signer certificate validity start should not be null", certificate.getValidityStart() != null);
            assertTrue("Signer certificate validity end should not be null", certificate.getValidityEnd() != null);
            assertTrue("Signer certificate key usage should not be null", certificate.getKeyUsage() != null);
            assertTrue("Signer certificate certificate policies should not be null",
                    certificate.getCertificatePolicies() != null);
            assertTrue("Signer certificate certificate policies should not be empty",
                    certificate.getCertificatePolicies().size() > 0);

            TestUtils.validateCertificateFieldsFromSampleCertificate(certificate);
            // Now let's verify the output file generated
            assertNotNull("Output file should not be null", outputFile);
            assertTrue("Output file should exist", Files.exists(outputFile));
            assertTrue("Output file should not be empty", Files.size(outputFile) > 0);

        } catch (Exception e) {
            // If PKI Express is not available or file/certificate is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute sign() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(outputFile);
            signer.dispose();
        }
    }

    /**
     * Complete CAdES signature flow test.
     * This test demonstrates the full signature process:
     * 1. Start: Use CadesSignatureStarter to prepare the signature and get a hash
     * 2. Sign: Use HashSigner (PKI Express sign-hash) to sign the hash
     * 3. Finish: Use SignatureFinisher to complete the signature
     * 
     * Based on the sample from:
     * https://github.com/LacunaSoftware/PkiSuiteSamples/blob/master/java/springmvc/src/main/java/com/lacunasoftware/pkisuite/controller/CadesSignatureExpressController.java
     */
    @Test
    public void testCompleteCadesSignatureFlow() throws IOException {
        // Create a sample file to sign (CAdES can sign any file, not just PDFs)
        InputStream fileToSign = TestUtils.LoadSamplePdf();

        // Create output file path
        Path outputCms = Files.createTempFile("test-signed-output", ".p7s");

        // Load PKCS12 file for signing
        InputStream pkcs12File = TestUtils.LoadSampleCertificateAsDERFormat();

        CadesSignatureStarter signatureStarter = null;
        HashSigner hashSigner = null;
        SignatureFinisher signatureFinisher = null;

        try {
            // Step 1: Start the signature process
            signatureStarter = new CadesSignatureStarter();

            // Set signature policy (similar to the sample - PkiBrazilCadesAdrBasica)
            signatureStarter.setSignaturePolicy(StandardSignaturePolicies.PkiBrazilCadesAdrBasica);

            // Set file to be signed
            signatureStarter.setFileToSign(fileToSign);

            // Set certificate for the starter
            signatureStarter.setCertificate(TestUtils.LoadSampleCertificateAsDERFormat());

            // Set the 'encapsulate content' option (default: true)
            signatureStarter.setEncapsulateContent(true);

            // Start the signature process - this calls
            // invoke(CommandEnum.CommandStartCades, args)
            SignatureStartResult startResult = signatureStarter.start();

            // Verify start result
            assertNotNull("Start result should not be null", startResult);
            assertNotNull("ToSignHash should not be null", startResult.getToSignHash());
            assertNotNull("DigestAlgorithm should not be null", startResult.getDigestAlgorithm());
            assertNotNull("TransferFile should not be null", startResult.getTransferFile());

            // Valid Digest algorithms are:
            // MD5("MD5"),
            // SHA1("SHA-1"),
            // SHA256("SHA-256"),
            // SHA384("SHA-384"),
            // SHA512("SHA-512");
            // So we'll iterate over DigestAlgorithm.values() and check if the name matches
            // the startResult.getDigestAlgorithm()
            boolean digestAlgorithmFound = false;
            String algorithmName = null;
            for (DigestAlgorithm algorithm : DigestAlgorithm.values()) {
                if (algorithm.getName().equals(startResult.getDigestAlgorithm())) {
                    digestAlgorithmFound = true;
                    algorithmName = algorithm.getName();
                    break;
                }
            }
            assertTrue("Digest Algorithm should be one of the following values: MD5, SHA-1, SHA-256, SHA-384, SHA-512",
                    digestAlgorithmFound);
            System.out.println("Digest Algorithm: " + algorithmName);

            // Step 2: Sign the hash using PKI Express sign-hash command
            hashSigner = new HashSigner();

            // Set the hash to sign
            hashSigner.setToSignHash(startResult.getToSignHash());

            // Set the digest algorithm
            hashSigner.setAlgorithm(startResult.getDigestAlgorithm());

            // Add test root for lacuna so we can test with test certificates
            hashSigner.setTrustLacunaTestRoot(true);

            // Set PKCS12 file for signing (this contains both certificate and private key)
            if (pkcs12File != null) {
                hashSigner.setPkcs12(TestUtils.LoadSamplePkcs12AsPath());
                hashSigner.setCertPassword(TestUtils.getSampleCertificatePassword());
            } else {
                throw new RuntimeException("PKCS12 file is required for HashSigner");
            }

            // Sign the hash - this calls invoke(CommandEnum.CommandSignHash, args)
            byte[] signatureBytes = hashSigner.sign();

            // Verify signature was created
            assertNotNull("Signature bytes should not be null", signatureBytes);
            assertTrue("Signature bytes should not be empty", signatureBytes.length > 0);

            // Convert signature to base64 for the finisher
            String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);

            // Step 3: Complete the signature process
            signatureFinisher = new SignatureFinisher();

            // Set the file to be signed (same as in start step)
            signatureFinisher.setFileToSign(fileToSign);

            // Set transfer file ID (from start step)
            signatureFinisher.setTransferFileId(startResult.getTransferFile());

            // Set the signature value
            signatureFinisher.setSignature(signatureBase64);

            // Set output file path
            signatureFinisher.setOutputFilePath(outputCms);

            // Add test root for lacuna so we can test with test certificates
            signatureFinisher.setTrustLacunaTestRoot(true);

            // Complete the signature - this calls invoke(CommandEnum.CommandCompleteSig,
            // args)
            PKCertificate signerCert = signatureFinisher.complete(true);

            // Verify completion
            assertNotNull("Signer certificate should not be null", signerCert);
            assertTrue("Output CMS file should exist", Files.exists(outputCms));
            assertTrue("Output CMS file should not be empty", Files.size(outputCms) > 0);

            TestUtils.validateCertificateFieldsFromSampleCertificate(signerCert);

            // Step 4: Verify the signature by exploring it
            CadesSignatureExplorer explorer = new CadesSignatureExplorer();
            explorer.setSignatureFile(outputCms);
            explorer.setTrustLacunaTestRoot(true);
            CadesSignature signature = explorer.open();

            assertNotNull("Signature should not be null", signature);
            assertTrue("Signature should have at least one signer", signature.getSigners().size() > 0);
            TestUtils.validateCertificateFieldsFromSampleCertificate(signature.getSigners().get(0).getCertificate(), true);

            explorer.dispose();

        } catch (RuntimeException e) {
            // if any exception is thrown, fail the test
            throw new AssertionError("Failed to execute complete signature flow: " + e.getMessage(), e);
        } finally {
            // Cleanup
            if (signatureStarter != null) {
                signatureStarter.dispose();
            }
            if (hashSigner != null) {
                hashSigner.dispose();
            }
            if (signatureFinisher != null) {
                signatureFinisher.dispose();
            }
            Files.deleteIfExists(outputCms);
        }
    }
}
