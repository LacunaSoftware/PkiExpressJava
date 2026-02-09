package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for Pkcs12Generator.
 * Tests the generate() method which contains an invoke() call.
 */
public class Pkcs12GeneratorTest {

    private Pkcs12Generator generator;

    @Before
    public void setUp() throws IOException {
        generator = new Pkcs12Generator();
    }

    @Test
    public void testGenerate_WithInvokeCall() throws IOException {
        // This test exercises the generate() method which calls invoke(CommandEnum.CommandCreatePfx, args)
        // Note: This requires a valid key and certificate file to work properly
        // The test verifies that the invoke() call is made successfully
        
        // Create a key generator to get a key in the proper format (JSON, XML, or BLOB)
        KeyGenerator keyGen = new KeyGenerator();
        keyGen.setKeySize(2048);
        keyGen.setKeyFormat(KeyFormats.BLOB);
        KeyGenerationResult keyResult = keyGen.generate();
        
        // Load the certificate file from test resources as an InputStream
        // The file is located in src/test/java/com/lacunasoftware/pkiexpress/resources/
        InputStream certInputStream = null;
        try {
            certInputStream = getClass().getResourceAsStream("resources/AlanTuring.cer");
            if (certInputStream == null) {
                throw new IOException("Certificate file 'AlanTuring.cer' not found in test resources");
            }
            
            // Set the certificate file using InputStream
            generator.setCertFile(certInputStream);
            
            // Set the key
            generator.setKey(keyResult.getKey());
            
            // Set password
            generator.setPassword("1234");
            
            // Execute the method that contains the invoke() call
            // This will make a concrete call to invoke()
            Pkcs12GenerationResult result = generator.generate();
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or certificate is invalid, 
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute generate() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            if (certInputStream != null) {
                try {
                    certInputStream.close();
                } catch (IOException e) {
                    // Ignore close errors
                }
            }
            keyGen.dispose();
            generator.dispose();
        }
    }
}
