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

/**
 * Integration tests for PadesSigner.
 * Tests the sign() method which contains an invoke() call.
 * Also includes a complete signature flow test using PadesSignatureStarter, HashSigner, and SignatureFinisher.
 */
public class PadesSignerTest {

    private PadesSigner signer;

    @BeforeEach
    public void setUp() throws IOException {
        signer = new PadesSigner();
    }

    @Test
    public void testSign_WithInvokeCall() throws IOException {
        // This test exercises sign(boolean getCert) which calls invoke(CommandEnum.CommandSignPades, args)
        // Note: This requires a valid PDF file and certificate/key to work properly
        // Load PDF from test resources
        InputStream pdfInputStream = getClass().getResourceAsStream("resources/SamplePdf.pdf");
        if (pdfInputStream == null) {
            throw new IOException("SamplePdf.pdf not found in test resources");
        }
        // Create output file path
        Path outputFile = Files.createTempFile("test-output", ".pdf");
        signer.setOutputFile(outputFile);
        
        // Execute the method that contains the invoke() call with getCert=true
        // This will make a concrete call to invoke()
        try {
            signer.setPkcs12(TestUtils.LoadSamplePkcs12AsPath());
            signer.setCertPassword(TestUtils.getSampleCertificatePassword());
            signer.setPdfToSign(pdfInputStream);
            signer.setTrustLacunaTestRoot(true); // added test root for lacuna so we can test with test certificates
            PKCertificate certificate = signer.sign(true);
            
            assertTrue(certificate.getThumbprint() != null, "Signer certificate thumbprint should not be null");
            assertTrue(certificate.getSubjectName() != null, "Signer certificate subject name should not be null");
            assertTrue(certificate.getIssuerName() != null, "Signer certificate issuer name should not be null");
            assertTrue(certificate.getSerialNumber() != null, "Signer certificate serial number should not be null");
            assertTrue(certificate.getValidityStart() != null, "Signer certificate validity start should not be null");
            assertTrue(certificate.getValidityEnd() != null, "Signer certificate validity end should not be null");
            assertTrue(certificate.getKeyUsage() != null, "Signer certificate key usage should not be null");
            assertTrue(certificate.getCertificatePolicies() != null, "Signer certificate certificate policies should not be null");
            assertTrue(certificate.getCertificatePolicies().size() > 0, "Signer certificate certificate policies should not be empty");

            TestUtils.validateCertificateFieldsFromSampleCertificate(certificate);
            // Now let's verify the output file generated
            assertNotNull(outputFile, "Output file should not be null");
            assertTrue(Files.exists(outputFile), "Output file should exist");
            assertTrue(Files.size(outputFile) > 0, "Output file should not be empty");

        } catch (Exception e) {
            // If PKI Express is not available or PDF/certificate is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute sign() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(outputFile);
            signer.dispose();
        }
    }

    /**
     * Complete PAdES signature flow test.
     * This test demonstrates the full signature process:
     * 1. Start: Use PadesSignatureStarter to prepare the signature and get a hash
     * 2. Sign: Use HashSigner (PKI Express sign-hash) to sign the hash
     * 3. Finish: Use SignatureFinisher to complete the signature
     * 
     * Based on the sample from:
     * https://github.com/LacunaSoftware/PkiSuiteSamples/blob/master/java/springmvc/src/main/java/com/lacunasoftware/pkisuite/controller/PadesSignatureExpressController.java
     * 
     * Note: This test requires a valid PKCS12 file with certificate and private key.
     * The PKCS12 file should be placed in the test resources or provided via system property.
     */
    @Test
    public void testCompletePadesSignatureFlow() throws IOException {
        // Load PDF from test resources
        InputStream pdfToSign = TestUtils.LoadSamplePdf();
        if (pdfToSign == null) {
            throw new IOException("SamplePdf.pdf not found in test resources");
        }
        
        // Create output file path
        Path outputPdf = Files.createTempFile("test-signed-output", ".pdf");
        
        // If no PKCS12 path is provided, try to generate one for testing
        InputStream pkcs12File = TestUtils.LoadSampleCertificateAsDERFormat();
        
        PadesSignatureStarter signatureStarter = null;
        HashSigner hashSigner = null;
        SignatureFinisher signatureFinisher = null;
        try {    
            // Step 2: Start the signature process
            signatureStarter = new PadesSignatureStarter();
            // Set signature policy (similar to the sample)
            signatureStarter.setSignaturePolicy(StandardSignaturePolicies.PadesBasic);
            // Set PDF to be signed
            signatureStarter.setPdfToSign(pdfToSign);
            // Set certificate for the starter
            // In practice, extract certificate from PKCS12 or use certificate file
            signatureStarter.setCertificate(TestUtils.LoadSampleCertificateAsDERFormat());
            // Start the signature process - this calls invoke(CommandEnum.CommandStartPades, args)
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
            // So we'll iterate over DigestAlgorithm.values() and check if the name matches the startResult.getDigestAlgorithm()
            boolean digestAlgorithmFound = false;
            String algorithmName = null;
            for (DigestAlgorithm algorithm : DigestAlgorithm.values()) {
                if (algorithm.getName().equals(startResult.getDigestAlgorithm())) {
                    digestAlgorithmFound = true;
                    algorithmName = algorithm.getName();
                    break;
                }
            }
            assertTrue(digestAlgorithmFound, "Digest Algorithm should be one of the following values: MD5, SHA-1, SHA-256, SHA-384, SHA-512");
            System.out.println("Digest Algorithm: " + algorithmName);

            // Step 3: Sign the hash using PKI Express sign-hash command
            hashSigner = new HashSigner();
            
            // Set the hash to sign
            hashSigner.setToSignHash(startResult.getToSignHash());
            
            // Set the digest algorithm
            hashSigner.setAlgorithm(startResult.getDigestAlgorithm());

            // Add test root for lacuna so we can test with test certificates
            hashSigner.setTrustLacunaTestRoot(true); //
            
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
            
            // Step 4: Complete the signature process
            signatureFinisher = new SignatureFinisher();
            
            // Set the file to be signed (same as in start step)
            signatureFinisher.setFileToSign(pdfToSign);
            
            // Set transfer file ID (from start step)
            signatureFinisher.setTransferFileId(startResult.getTransferFile());
            
            // Set the signature value
            signatureFinisher.setSignature(signatureBase64);
            
            // Set output file path
            signatureFinisher.setOutputFilePath(outputPdf);

            // Add test root for lacuna so we can test with test certificates
            signatureFinisher.setTrustLacunaTestRoot(true);
            
            // Complete the signature - this calls invoke(CommandEnum.CommandCompleteSig, args)
            PKCertificate signerCert = signatureFinisher.complete(true);
            
            // Verify completion
            assertNotNull(signerCert, "Signer certificate should not be null");
            assertTrue(Files.exists(outputPdf), "Output PDF should exist");
            assertTrue(Files.size(outputPdf) > 0, "Output PDF should not be empty");

            TestUtils.validateCertificateFieldsFromSampleCertificate(signerCert);

            // Step 5: Verify the signature by exploring it
            PadesSignatureExplorer explorer = new PadesSignatureExplorer();
            explorer.setSignatureFile(outputPdf);
            explorer.setTrustLacunaTestRoot(true);
            PadesSignature signature = explorer.open();
            assertNotNull(signature, "Signature should not be null");
            assertTrue(signature.getSigners().size() > 0, "Signature should have at least one signer");

            // Validate the signature certificate fields
            TestUtils.validateCertificateFieldsFromSampleCertificate(signature.getSigners().get(0).getCertificate(), true);
            explorer.dispose();

        } catch (RuntimeException e) {  // if any exception is thrown, fail the test
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
            Files.deleteIfExists(outputPdf);
        }
    }
}
