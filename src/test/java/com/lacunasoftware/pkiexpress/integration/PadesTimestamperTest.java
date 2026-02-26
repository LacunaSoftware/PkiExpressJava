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


/**
 * Integration tests for PadesTimestamper.
 * Tests the stamp() method which contains an invoke() call.
 */
public class PadesTimestamperTest {

    private PadesTimestamper timestamper;

    @BeforeEach
    public void setUp() throws IOException {
        timestamper = new PadesTimestamper();
    }

    @Test
    public void testStamp_WithInvokeCall() throws IOException {
        // This test exercises stamp() which calls invoke(CommandEnum.CommandStampPdf, args)
        // Note: This requires a valid PDF file to work properly
        
        // Create a temporary PDF file (minimal PDF structure)
        InputStream pdfFile = TestUtils.LoadSamplePdf();
        timestamper.setPdf(pdfFile);
        timestamper.setTrustLacunaTestRoot(true);
        timestamper.setTimestampAuthority(new TimestampAuthority("https://tsa.lacunasoftware.com"));
        timestamper.setOverwriteOriginalFile(false);
        
        // Create output file path
        Path outputFile = Files.createTempFile("test-output", ".pdf");
        timestamper.setOutputFilePath(outputFile);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            timestamper.stamp();
            // TODO: Use SignatureExplorer to check if timestamp is correct and other properties from
            // stamped documents
            assertNotNull(outputFile, "Output file should not be null");
            assertTrue(Files.exists(outputFile), "Output file should exist");
            assertTrue(Files.size(outputFile) > 0, "Output file should not be empty");
        } catch (Exception e) {
            // If PKI Express is not available or PDF is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute stamp() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(outputFile);
            timestamper.dispose();
        }
    }
}
