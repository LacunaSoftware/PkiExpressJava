package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration tests for Metadata.
 * Tests the getPkiExpressVersion() method which contains an invoke() call.
 */
public class MetadataTest {

    private Metadata metadata;

    @BeforeEach
    public void setUp() throws IOException {
        metadata = new Metadata();
    }

    @Test
    public void testGetPkiExpressVersion_WithInvokeCall() throws IOException {
        // This test exercises getPkiExpressVersion() which calls invoke(CommandEnum.CommandVersion, args)
        // The test verifies that the invoke() call is made successfully
        try {
            String version = metadata.getPkiExpressVersion();
            assertNotNull(version, "Version should not be null");
            assertFalse(version.isEmpty(), "Version should not be empty");
        } catch (Exception e) {
            // If PKI Express is not available, the test will fail
            // but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute getPkiExpressVersion() with invoke() call: " + e.getMessage(), e);
        }
    }
}
