package com.lacunasoftware.pkiexpress;

import org.junit.Test;
import org.junit.Before;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * Integration tests for CertificateReader.
 * Tests the decode() method which contains an invoke() call.
 */
public class CertificateReaderTest {

    private CertificateReader reader;

    @Before
    public void setUp() throws IOException {
        reader = new CertificateReader();
    }

    @Test
    public void testDecode_WithInvokeCall() throws IOException {
        // This test exercises decode() which calls invoke(CommandEnum.CommandReadCert, args)
        // Note: This requires a valid certificate file to work properly
        
        // Create a temporary certificate file (minimal certificate structure)
        Path tempCertFile = Files.createTempFile("test-cert", ".cer");
        Files.write(tempCertFile, "dummy certificate content".getBytes());
        reader.setCert(tempCertFile);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            PKCertificate result = reader.decode();
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or certificate is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute decode() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(tempCertFile);
            reader.dispose();
        }
    }
}
