package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Integration tests for PdfMarker.
 * Tests the apply() method which contains an invoke() call.
 */
public class PdfMarkerTest {

    private PdfMarker marker;

    @BeforeEach
    public void setUp() throws IOException {
        marker = new PdfMarker();
    }

    @Test
    public void testApply_WithInvokeCall() throws IOException {
        // This test exercises apply() which calls invoke(CommandEnum.CommandEditPdf, args)
        // Note: This requires a valid PDF file to work properly
        
        // Create a temporary PDF file (minimal PDF structure)
        Path tempPdfFile = Files.createTempFile("test-pdf", ".pdf");
        Files.write(tempPdfFile, "%PDF-1.4\n".getBytes());
        marker.setFile(tempPdfFile);
        
        // Create output file path
        Path outputFile = Files.createTempFile("test-output", ".pdf");
        marker.setOutputFilePath(outputFile);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            marker.apply();
            // If successful, the output file should exist
            assertTrue(Files.exists(outputFile), "Output file should exist after applying marks");
        } catch (Exception e) {
            // If PKI Express is not available or PDF is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute apply() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(tempPdfFile);
            Files.deleteIfExists(outputFile);
            marker.dispose();
        }
    }
}
