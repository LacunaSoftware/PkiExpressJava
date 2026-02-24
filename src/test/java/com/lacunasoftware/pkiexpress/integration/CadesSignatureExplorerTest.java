package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;


import com.lacunasoftware.pkiexpress.TestUtils;

/**
 * Integration tests for CadesSignatureExplorer.
 * Tests the open() method which contains an invoke() call.
 */
public class CadesSignatureExplorerTest {

    private CadesSignatureExplorer explorer;

    @BeforeEach
    public void setUp() throws IOException {
        explorer = new CadesSignatureExplorer();
    }

    @Test
    public void testOpen_WithInvokeCall() throws IOException {
        // This test exercises open() which calls invoke(CommandEnum.CommandOpenCades, args)
        // Note: This requires a valid CAdES signature file to work properly
        
        // Create a temporary signature file (minimal structure)
        Path signatureFile = TestUtils.LoadSignedSampleCmsFile();
        explorer.setSignatureFile(signatureFile);
        explorer.setTrustLacunaTestRoot(true);
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            CadesSignature result = explorer.open();
            assertNotNull(result, "Result should not be null");
            assertTrue(result.getSigners().size() > 0, "Result should have at least one signer");
            TestUtils.validateCertificateFieldsFromSampleCertificate(result.getSigners().get(0).getCertificate());
        } catch (Exception e) {
            // If PKI Express is not available or signature file is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute open() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            explorer.dispose();
        }
    }
}
