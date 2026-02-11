package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

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
        
        InputStream certFile = TestUtils.LoadSampleCertificateAsPEMFormat();
        reader.setCert(certFile);  
        reader.setTrustLacunaTestRoot(true);
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
            reader.dispose();
        }
    }
}
