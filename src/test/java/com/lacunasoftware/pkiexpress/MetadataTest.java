package com.lacunasoftware.pkiexpress;

import org.junit.Test;
import org.junit.Before;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Integration tests for Metadata.
 * Tests the getPkiExpressVersion() method which contains an invoke() call.
 */
public class MetadataTest {

    private Metadata metadata;

    @Before
    public void setUp() throws IOException {
        metadata = new Metadata();
    }

    @Test
    public void testGetPkiExpressVersion_WithInvokeCall() throws IOException {
        // This test exercises getPkiExpressVersion() which calls invoke(CommandEnum.CommandVersion, args)
        // The test verifies that the invoke() call is made successfully
        try {
            String version = metadata.getPkiExpressVersion();
            assertNotNull("Version should not be null", version);
            assertFalse("Version should not be empty", version.isEmpty());
        } catch (Exception e) {
            // If PKI Express is not available, the test will fail
            // but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute getPkiExpressVersion() with invoke() call: " + e.getMessage(), e);
        }
    }
}
