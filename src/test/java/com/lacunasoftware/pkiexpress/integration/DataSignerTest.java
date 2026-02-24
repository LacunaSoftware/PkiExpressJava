package com.lacunasoftware.pkiexpress.integration;
import com.lacunasoftware.pkiexpress.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Base64;

import com.lacunasoftware.pkiexpress.TestUtils;

/**
 * Integration tests for DataSigner.
 * Tests the sign() method which contains an invoke() call.
 */
public class DataSignerTest {

    private DataSigner signer;

    @BeforeEach
    public void setUp() throws IOException {
        signer = new DataSigner();
    }

    @Test
    public void testSign_WithInvokeCall() throws IOException {
        // This test exercises sign() which calls invoke(CommandEnum.CommandSignData, args)
        // Note: This requires valid data and certificate/key to work properly
        
        // Set data to sign (base64 encoded)
        byte[] dataBytes = "test data to sign".getBytes();
        String dataBase64 = Base64.getEncoder().encodeToString(dataBytes);
        signer.setToSignData(dataBase64);
        signer.setPkcs12(TestUtils.LoadSamplePkcs12AsPath());
        signer.setCertPassword(TestUtils.getSampleCertificatePassword());

        signer.setTrustLacunaTestRoot(true);
        signer.setSignaturePolicy(StandardSignaturePolicies.PadesBasic);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            byte[] result = signer.sign();
            assertNotNull(result, "Result should not be null");
            
        } catch (Exception e) {
            // If PKI Express is not available or certificate/key is not set,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute sign() with invoke() call: " + e.getMessage(), e);
        } finally {
            signer.dispose();
        }
    }
}
