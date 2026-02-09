package com.lacunasoftware.pkiexpress;

import org.junit.Test;
import org.junit.Before;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import static org.junit.Assert.*;

/**
 * Integration tests for SignatureFinisher.
 * Tests the complete() method which contains an invoke() call.
 */
public class SignatureFinisherTest {

    private SignatureFinisher finisher;

    @Before
    public void setUp() throws IOException {
        finisher = new SignatureFinisher();
    }

    @Test
    public void testComplete_WithInvokeCall() throws IOException {
        // This test exercises complete(boolean getCert) which calls invoke(CommandEnum.CommandCompleteSig, args)
        // Note: This requires valid file, transfer file, signature, and output path to work properly
        
        // Create a temporary file to sign
        Path tempFile = Files.createTempFile("test-file", ".pdf");
        Files.write(tempFile, "test content".getBytes());
        finisher.setFileToSign(tempFile);
        
        // Create a transfer file ID
        String transferFileId = "test-transfer-id";
        finisher.setTransferFileId(transferFileId);
        
        // Create a dummy signature (base64 encoded)
        byte[] signatureBytes = "dummy signature".getBytes();
        String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);
        finisher.setSignature(signatureBase64);
        
        // Create output file path
        Path outputFile = Files.createTempFile("test-output", ".pdf");
        finisher.setOutputFilePath(outputFile);
        
        // Execute the method that contains the invoke() call with getCert=true
        // This will make a concrete call to invoke()
        try {
            PKCertificate result = finisher.complete(true);
            // Result may be null if the signature is invalid, but invoke() was still called
        } catch (Exception e) {
            // If PKI Express is not available or parameters are invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute complete() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(tempFile);
            Files.deleteIfExists(outputFile);
            finisher.dispose();
        }
    }
}
