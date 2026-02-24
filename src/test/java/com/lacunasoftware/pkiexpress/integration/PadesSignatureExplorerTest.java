package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;


/**
 * Integration tests for PadesSignatureExplorer.
 * Tests the open() method which contains an invoke() call.
 */
public class PadesSignatureExplorerTest {

    private PadesSignatureExplorer explorer;

    @BeforeEach
    public void setUp() throws IOException {
        explorer = new PadesSignatureExplorer();
    }

    @Test
    public void testOpen_WithInvokeCall() throws IOException {
        // This test exercises open() which calls invoke(CommandEnum.CommandOpenPades, args)
        // Note: This requires a valid signed PDF file to work properly
        
        // Load signed sample PDF file
        Path signedSamplePdf = TestUtils.LoadSignedSamplePdf();
        explorer.setSignatureFile(signedSamplePdf);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            PadesSignature result = explorer.open();
            assertNotNull(result, "Result should not be null");
            assertTrue(result.getSigners().size() > 0, "Result should have at least one signer");
            TestUtils.validateCertificateFieldsFromSampleCertificate(result.getSigners().get(0).getCertificate(), true);
        } catch (Exception e) {
            // If PKI Express is not available or PDF is invalid/unsigned,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute open() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            explorer.dispose();
        }
    }
}
