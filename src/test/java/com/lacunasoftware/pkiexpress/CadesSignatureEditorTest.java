package com.lacunasoftware.pkiexpress;

import org.junit.Test;
import org.junit.Before;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * Integration tests for CadesSignatureEditor.
 * Tests the merge() method which contains an invoke() call.
 */
public class CadesSignatureEditorTest {

    private CadesSignatureEditor editor;

    @Before
    public void setUp() throws IOException {
        editor = new CadesSignatureEditor();
    }

    @Test
    public void testMerge_WithInvokeCall() throws IOException {
        // This test exercises merge() which calls invoke(CommandEnum.CommandMergeCms, args)
        // Note: This requires valid CMS/CAdES files to work properly
        
        // Create temporary CMS files
        Path tempCmsFile1 = Files.createTempFile("test-cms1", ".p7s");
        Files.write(tempCmsFile1, "dummy cms content 1".getBytes());
        editor.addCmsFile(tempCmsFile1);
        
        Path tempCmsFile2 = Files.createTempFile("test-cms2", ".p7s");
        Files.write(tempCmsFile2, "dummy cms content 2".getBytes());
        editor.addCmsFile(tempCmsFile2);
        
        // Create output file path
        Path outputFile = Files.createTempFile("test-output", ".p7s");
        editor.setOutputFilePath(outputFile);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            editor.merge();
            // If successful, the output file should exist
            assertTrue("Output file should exist after merging", Files.exists(outputFile));
        } catch (Exception e) {
            // If PKI Express is not available or CMS files are invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute merge() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(tempCmsFile1);
            Files.deleteIfExists(tempCmsFile2);
            Files.deleteIfExists(outputFile);
            editor.dispose();
        }
    }
}
