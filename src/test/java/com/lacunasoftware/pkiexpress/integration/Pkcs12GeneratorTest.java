package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


/**
 * Integration tests for Pkcs12Generator.
 * Tests the generate() method which contains an invoke() call.
 */
public class Pkcs12GeneratorTest {

    private Pkcs12Generator generator;

    @BeforeEach
    public void setUp() throws IOException {
        generator = new Pkcs12Generator();
    }

    @Test
    public void testGenerate_WithInvokeCall() throws IOException {
        // This test exercises the generate() method which calls
        // invoke(CommandEnum.CommandCreatePfx, args)
        // Note: This requires a valid key and certificate file to work properly
        // The test verifies that the invoke() call is made successfully
        try {
            // Create a key generator to get a key in the proper format (JSON, XML, or BLOB)
            KeyGenerator keyGen = new KeyGenerator();
            keyGen.setKeySize(2048);
            keyGen.setKeyFormat(KeyFormats.BLOB);
            KeyGenerationResult keyResult = keyGen.generate();

            // Load the certificate file from test resources as an InputStream
            // The file is located in src/test/java/com/lacunasoftware/pkiexpress/resources/
            generator.setCertFile(TestUtils.LoadSampleCertificateAsPEMFormat());
            generator.setKey(keyResult.getKey());
            generator.setPassword(TestUtils.getSampleCertificatePassword());
            Pkcs12GenerationResult result = generator.generate();
            assertNotNull(result, "Result should not be null");
        } catch (Exception e) {
            // If PKI Express is not available or certificate is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute generate() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            generator.dispose();
        }
    }
}
