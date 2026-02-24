package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;


import com.lacunasoftware.pkiexpress.TestUtils;

/**
 * Integration tests for CadesSigner.
 * Tests the sign() method which contains an invoke() call.
 * Also includes a complete signature flow test using CadesSignatureStarter,
 * HashSigner, and SignatureFinisher.
 */
public class CadesSignerTest {

    private CadesSigner signer;

    @BeforeEach
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

            assertTrue(certificate.getThumbprint() != null, "Signer certificate thumbprint should not be null");
            assertTrue(certificate.getSubjectName() != null, "Signer certificate subject name should not be null");
            assertTrue(certificate.getIssuerName() != null, "Signer certificate issuer name should not be null");
            assertTrue(certificate.getSerialNumber() != null, "Signer certificate serial number should not be null");
            assertTrue(certificate.getValidityStart() != null, "Signer certificate validity start should not be null");
            assertTrue(certificate.getValidityEnd() != null, "Signer certificate validity end should not be null");
            assertTrue(certificate.getKeyUsage() != null, "Signer certificate key usage should not be null");
            assertTrue(certificate.getCertificatePolicies() != null,
                    "Signer certificate certificate policies should not be null");
            assertTrue(certificate.getCertificatePolicies().size() > 0,
                    "Signer certificate certificate policies should not be empty");

            TestUtils.validateCertificateFieldsFromSampleCertificate(certificate);
            // Now let's verify the output file generated
            assertNotNull(outputFile, "Output file should not be null");
            assertTrue(Files.exists(outputFile), "Output file should exist");
            assertTrue(Files.size(outputFile) > 0, "Output file should not be empty");

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
            assertNotNull(startResult, "Start result should not be null");
            assertNotNull(startResult.getToSignHash(), "ToSignHash should not be null");
            assertNotNull(startResult.getDigestAlgorithm(), "DigestAlgorithm should not be null");
            assertNotNull(startResult.getTransferFile(), "TransferFile should not be null");

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
            assertTrue(digestAlgorithmFound,
                    "Digest Algorithm should be one of the following values: MD5, SHA-1, SHA-256, SHA-384, SHA-512");
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
            assertNotNull(signatureBytes, "Signature bytes should not be null");
            assertTrue(signatureBytes.length > 0, "Signature bytes should not be empty");

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
            assertNotNull(signerCert, "Signer certificate should not be null");
            assertTrue(Files.exists(outputCms), "Output CMS file should exist");
            assertTrue(Files.size(outputCms) > 0, "Output CMS file should not be empty");

            TestUtils.validateCertificateFieldsFromSampleCertificate(signerCert);

            // Step 4: Verify the signature by exploring it
            CadesSignatureExplorer explorer = new CadesSignatureExplorer();
            explorer.setSignatureFile(outputCms);
            explorer.setTrustLacunaTestRoot(true);
            CadesSignature signature = explorer.open();

            assertNotNull(signature, "Signature should not be null");
            assertTrue(signature.getSigners().size() > 0, "Signature should have at least one signer");
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
