package com.lacunasoftware.pkiexpress;

import org.junit.Test;
import org.junit.Before;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * Integration tests for PadesTimestamper.
 * Tests the stamp() method which contains an invoke() call.
 */
public class PadesTimestamperTest {

    private PadesTimestamper timestamper;

    @Before
    public void setUp() throws IOException {
        timestamper = new PadesTimestamper();
    }

    @Test
    public void testStamp_WithInvokeCall() throws IOException {
        // This test exercises stamp() which calls invoke(CommandEnum.CommandStampPdf, args)
        // Note: This requires a valid PDF file to work properly
        
        // Create a temporary PDF file (minimal PDF structure)
        Path tempPdfFile = Files.createTempFile("test-pdf", ".pdf");
        Files.write(tempPdfFile, "%PDF-1.4\n".getBytes());
        timestamper.setPdf(tempPdfFile);
        
        // Create output file path
        Path outputFile = Files.createTempFile("test-output", ".pdf");
        timestamper.setOutputFilePath(outputFile);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            timestamper.stamp();
            // If successful, the output file should exist
            assertTrue("Output file should exist after stamping", Files.exists(outputFile));
        } catch (Exception e) {
            // If PKI Express is not available or PDF is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute stamp() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(tempPdfFile);
            Files.deleteIfExists(outputFile);
            timestamper.dispose();
        }
    }
}
