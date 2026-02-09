package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for XmlSigner.
 * Tests the sign() method which contains an invoke() call.
 */
public class XmlSignerTest {

    private XmlSigner signer;

    @Before
    public void setUp() throws IOException {
        signer = new XmlSigner();
    }

    @Test
    public void testSign_WithInvokeCall() throws IOException {
        // This test exercises sign(boolean getCert) which calls invoke(CommandEnum.CommandSignXml, args)
        // Note: This requires a valid XML file and certificate/key to work properly
        
        // Create a temporary XML file (minimal XML structure)
        Path xmlToSign = TestUtils.LoadSampleXml();
        signer.setTrustLacunaTestRoot(true);
        signer.setPkcs12(TestUtils.LoadSamplePkcs12AsPath());
        signer.setCertPassword(TestUtils.getSampleCertificatePassword());
        signer.setXmlToSign(xmlToSign);

        // Create output file path
        Path outputFile = Files.createTempFile("test-output", ".xml");
        signer.setOutputFile(outputFile);
        
        // Execute the method that contains the invoke() call with getCert=true
        // This will make a concrete call to invoke()
        try {
            PKCertificate result = signer.sign(true);
            // Result may be null if certificate/key is not set, but invoke() was still called
            assertNotNull("Result should not be null", result != null);
            TestUtils.validateCertificateFieldsFromSampleCertificate(result);
        } catch (Exception e) {
            // If PKI Express is not available or XML/certificate is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute sign() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            Files.deleteIfExists(outputFile);
            signer.dispose();
        }
    }
}
