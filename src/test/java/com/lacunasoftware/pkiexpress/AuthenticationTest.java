package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for Authentication.
 * Tests all methods that contain invoke() calls.
 * Each method with an invoke() call gets its own test case.
 */
public class AuthenticationTest {

    private Authentication authentication;

    @Before
    public void setUp() throws IOException {
        authentication = new Authentication();
    }

    @Test
    public void testStart_WithInvokeCall() throws IOException {
        // This test exercises start() which calls invoke(CommandEnum.CommandStartAuth,
        // args)
        // The test verifies that the invoke() call is made successfully
        try {
            AuthStartResult authStartResult = authentication.start();
            assertNotNull("Result should not be null", authStartResult);
            assertNotNull("Nonce should not be null", authStartResult.getNonce());
        } catch (Exception e) {
            // If PKI Express is not available, the test will fail
            // but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute start() with invoke() call: " + e.getMessage(), e);
        } finally {
            authentication.dispose();
        }
    }

    @Test
    public void testComplete_WithInvokeCall() throws IOException {
        // This test exercises complete() which calls
        // invoke(CommandEnum.CommandCompleteAuth, args)
        // Note: This requires a valid nonce, certificate, and signature to work
        // properly

        try {
            // First, get a nonce from start()
            AuthStartResult authStartResult = authentication.start();
            // Let's use pkie's sign-data command to create a signature from the nonce
            DataSigner dataSigner = new DataSigner();
            dataSigner.setToSignData(authStartResult.getNonce());
            dataSigner.setPkcs12(TestUtils.LoadSamplePkcs12AsPath());
            dataSigner.setCertPassword(TestUtils.getSampleCertificatePassword());
            // Sign the nonce
            byte[] signature = dataSigner.sign();
            // Set the signature
            authentication.setSignature(signature);
            // Set the nonce
            authentication.setNonce(authStartResult.getNonce());
            // Set the certificate content.
            authentication.setCertificate(getClass().getResourceAsStream("resources/AlanTuring.cer"));
            // Set the signature.
            authentication.setSignature(signature);

            // Complete the authentication. Receive as response a AuthCompleteResult
            // instance containing
            // the following fields:
            // - The certificate information;
            // - The validation results;
            AuthCompleteResult result = authentication.complete();
            assertNotNull("Result should not be null", result);
            TestUtils.validateCertificateFieldsFromSampleCertificate(result.getCertificate()); // validate the certificate fields
        } catch (Exception e) {
            // If PKI Express is not available or certificate/signature is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute complete() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            // Files.deleteIfExists(tempCertFile);
            authentication.dispose();
        }
    }
}
