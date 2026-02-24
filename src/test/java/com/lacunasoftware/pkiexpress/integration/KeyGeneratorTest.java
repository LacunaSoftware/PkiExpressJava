package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration tests for KeyGenerator.
 * Tests the generate() method which contains an invoke() call.
 */
public class KeyGeneratorTest {

    private KeyGenerator generator;

    @BeforeEach
    public void setUp() throws IOException {
        generator = new KeyGenerator();
    }

    @Test
    public void testGenerate_WithInvokeCall() throws IOException {
        // This test exercises the generate() method which calls invoke(CommandEnum.CommandGenKey, args)
        // The test verifies that the invoke() call is made successfully
        
        // Set key size
        generator.setKeySize(2048);
        
        // Set key format
        generator.setKeyFormat(KeyFormats.JSON);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            KeyGenerationResult result = generator.generate();
            assertNotNull(result, "Result should not be null");
            assertNotNull(result.getKey(), "Key should not be null");
        } catch (Exception e) {
            // If PKI Express is not available, the test will fail
            // but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute generate() with invoke() call: " + e.getMessage(), e);
        } finally {
            generator.dispose();
        }
    }
}
