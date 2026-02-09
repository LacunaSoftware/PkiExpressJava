package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for TrustServicesManager.
 * Tests all methods that contain invoke() calls.
 * Each method with an invoke() call gets its own test case.
 */

// IMPORTANT:
// In order for these tests to work, you need to have a valid service and CPF/CNPJ.
// To know more about configuring trust services, please refer to the documentation:
// https://docs.lacunasoftware.com/pt-br/articles/pki-express/config/trust-services.html
// You need to perform this configuration in the PKI Express application you're using to test these use cases.
// Unfortunately, we cannot provide a valid service and CPF/CNPJ for you to use in this test.
// as cloud certificates are not available for testing purposes. So you need to use a personal certificate.


public class TrustServicesManagerTest {

    private TrustServicesManager manager;
    String defaultTestCpf;
    String defaultTestCnpj;
    @Before
    public void setUp() throws IOException {
        // You need to use your own CPF/CNPJ as cloud certificates are not available for testing purposes.
        // Below we use the CPF of Alan Mathison Turing (Lacuna CA v7)
        defaultTestCpf = "04300187118";
        // Below we use the CNPJ of Wayne Enterprises (Lacuna CA v7)
        defaultTestCnpj = "34785515000166";
        manager = new TrustServicesManager();
    }

    @Test
    public void testCheckByCpf_WithInvokeCall() throws IOException {
        // This test exercises checkByCpf() which calls invoke(CommandEnum.CommandCheckService, args)
        // Note: This requires valid service and CPF to work properly
        try {
            CheckServiceResult result = manager.checkByCpf("safeID", defaultTestCpf);
            // check if userHasCertificates is a valid boolean
            assertNotNull("Result should not be null", result);
            assertNotNull(result.isUserHasCertificates());
            System.out.println("User has certificates: " + result.isUserHasCertificates());
        } catch (Exception e) {
            // If PKI Express is not available or service is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute checkByCpf() with invoke() call: " + e.getMessage(), e);
        } finally {
            manager.dispose();
        }
    }

    @Test
    public void testCheckByCnpj_WithInvokeCall() throws IOException {
        // This test exercises checkByCnpj() which calls invoke(CommandEnum.CommandCheckService, args)
        // Note: This requires valid service and CNPJ to work properly
        try {
            CheckServiceResult result = manager.checkByCnpj("test-service", defaultTestCnpj);
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or service is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute checkByCnpj() with invoke() call: " + e.getMessage(), e);
        } finally {
            manager.dispose();
        }
    }

    @Test
    public void testDiscoverByCpf_WithInvokeCall() throws IOException {
        // This test exercises discoverByCpf() which calls invoke(CommandEnum.CommandDiscoverServices, args)
        // Note: This requires valid CPF to work properly
        try {
            List<TrustServiceInfo> result = manager.discoverByCpf(defaultTestCpf);
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or CPF is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute discoverByCpf() with invoke() call: " + e.getMessage(), e);
        } finally {
            manager.dispose();
        }
    }

    @Test
    public void testDiscoverByCnpj_WithInvokeCall() throws IOException {
        // This test exercises discoverByCnpj() which calls invoke(CommandEnum.CommandDiscoverServices, args)
        // Note: This requires valid CNPJ to work properly
        try {
            List<TrustServiceInfo> result = manager.discoverByCnpj(defaultTestCnpj);
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or CNPJ is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute discoverByCnpj() with invoke() call: " + e.getMessage(), e);
        } finally {
            manager.dispose();
        }
    }

    @Test
    public void testDiscoverByCpfAndStartAuth_WithInvokeCall() throws IOException {
        // This test exercises discoverByCpfAndStartAuth() which calls invoke(CommandEnum.CommandDiscoverServices, args)
        // Note: This requires valid CPF and redirectUrl to work properly
        try {
            List<TrustServiceAuthParameters> result = manager.discoverByCpfAndStartAuth(
                defaultTestCpf, 
                "https://example.com/redirect",
                TrustServiceSessionTypes.SIGNATURE_SESSION
            );
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or parameters are invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute discoverByCpfAndStartAuth() with invoke() call: " + e.getMessage(), e);
        } finally {
            manager.dispose();
        }
    }

    @Test
    public void testDiscoverByCnpjAndStartAuth_WithInvokeCall() throws IOException {
        // This test exercises discoverByCnpjAndStartAuth() which calls invoke(CommandEnum.CommandDiscoverServices, args)
        // Note: This requires valid CNPJ and redirectUrl to work properly
        try {
            List<TrustServiceAuthParameters> result = manager.discoverByCnpjAndStartAuth(
                defaultTestCnpj,
                "https://example.com/redirect",
                TrustServiceSessionTypes.SIGNATURE_SESSION
            );
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or parameters are invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute discoverByCnpjAndStartAuth() with invoke() call: " + e.getMessage(), e);
        } finally {
            manager.dispose();
        }
    }

    @Test
    public void testPasswordAuthorize_WithInvokeCall() throws IOException {
        // This test exercises passwordAuthorize() which calls invoke(CommandEnum.CommandPasswordAuthorize, args)
        // Note: This requires valid service, username, and password to work properly
        try {
            TrustServiceSessionResult result = manager.passwordAuthorize(
                "test-service",
                "test-username",
                "test-password",
                TrustServiceSessionTypes.SIGNATURE_SESSION
            );
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or credentials are invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute passwordAuthorize() with invoke() call: " + e.getMessage(), e);
        } finally {
            manager.dispose();
        }
    }

    @Test
    public void testCompleteAuth_WithInvokeCall() throws IOException {
        // This test exercises completeAuth() which calls invoke(CommandEnum.CommandCompleteServiceAuth, args)
        // Note: This requires valid code and state to work properly
        try {
            TrustServiceSessionResult result = manager.completeAuth("test-code", "test-state");
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // If PKI Express is not available or parameters are invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute completeAuth() with invoke() call: " + e.getMessage(), e);
        } finally {
            manager.dispose();
        }
    }
}
