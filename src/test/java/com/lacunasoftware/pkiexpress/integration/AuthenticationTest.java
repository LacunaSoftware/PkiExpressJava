package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

/**
 * Integration tests for Authentication.
 * Tests all methods that contain invoke() calls.
 * Each method with an invoke() call gets its own test case.
 */
@DisplayName("Authentication Integration Tests")
public class AuthenticationTest {

    private Authentication authentication;

    @BeforeEach
    public void setUp() throws IOException {
        authentication = new Authentication();
    }

    @AfterEach
    public void tearDown() {
        if (authentication != null) {
            authentication.dispose();
        }
    }

    @Test
    @DisplayName("Should return AuthStartResult with nonce when start is called")
    public void shouldReturnAuthStartResultWithNonce() throws IOException {
        // This test exercises start() which calls invoke(CommandEnum.CommandStartAuth,
        // args)
        // The test verifies that the invoke() call is made successfully
        try {
            AuthStartResult authStartResult = authentication.start();
            assertNotNull(authStartResult, "Result should not be null");
            assertNotNull(authStartResult.getNonce(), "Nonce should not be null");
        } catch (Exception e) {
            // If PKI Express is not available, the test will fail
            // but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute start() with invoke() call: " + e.getMessage(), e);
        }
    }

    @Test
    @DisplayName("Should complete authentication with invoke call")
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
            assertNotNull(result, "Result should not be null");
            TestUtils.validateCertificateFieldsFromSampleCertificate(result.getCertificate()); // validate the certificate fields
        } catch (Exception e) {
            // If PKI Express is not available or certificate/signature is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute complete() with invoke() call: " + e.getMessage(), e);
        }
    }
}
