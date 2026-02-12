package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for CadesSignatureExplorer.
 * Tests the open() method which contains an invoke() call.
 */
public class CadesSignatureExplorerTest {

    private CadesSignatureExplorer explorer;

    @Before
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
            assertNotNull("Result should not be null", result);
            assertTrue("Result should have at least one signer", result.getSigners().size() > 0);
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
