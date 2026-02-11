package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for CertificateExplorer.
 * Tests the open() method which contains an invoke() call.
 */
public class CertificateExplorerTest {

    private CertificateExplorer explorer;
    private CertificateExplorer explorer2;

    @Before
    public void setUp() throws IOException {
        explorer = new CertificateExplorer();
        explorer2 = new CertificateExplorer();
    }

    @Test
    public void testOpen_WithInvokeCall() throws IOException {
        // This test exercises open() which calls invoke(CommandEnum.CommandOpenCertificate, args)
        // Note: This requires a valid certificate file to work properly
        
        // Read .cer (encoded in DER format) file located in src/test/java/com/lacunasoftware/pkiexpress/resources/AlanTuring-DER.cer
        // (it can be read as PEM too)
        InputStream DERInputStream = getClass().getResourceAsStream("resources/AlanTuring-DER.cer");
        if (DERInputStream == null) {
            throw new IOException("PFX file 'AlanTuring-DER.cer' not found in test resources");
        }
        explorer.setCertificate(DERInputStream);

        // Read .cer (encoded in PEM format) file located in src/test/java/com/lacunasoftware/pkiexpress/resources/AlanTuring-PEM.cer
        InputStream PEMInputStream = getClass().getResourceAsStream("resources/AlanTuring-PEM.cer");
        if (PEMInputStream == null) {
            throw new IOException("PFX file 'AlanTuring-PEM.cer' not found in test resources");
        }
        explorer2.setCertificate(PEMInputStream);
        
        // Execute the method that contains the invoke() call
        // This will make a concrete call to invoke()
        try {
            CertificateExplorerResult result = explorer.open();
            CertificateExplorerResult result2 = explorer2.open();
            assertNotNull("Result should not be null", result);
            assertNotNull("Result2 should not be null", result2);
            // Assert common fields
            assertNotNull("Common name should not be null", result.getCertificate().getSubjectName().getCommonName());

            // Compare both certificate fields (should be the same since the certificate is the same)
            assertEquals(result.getCertificate().getSubjectName().getCommonName(), result2.getCertificate().getSubjectName().getCommonName());
            assertEquals(result.getCertificate().getThumbprint(), result2.getCertificate().getThumbprint());
            assertEquals(result.getCertificate().getSubjectName().getCountry(), result2.getCertificate().getSubjectName().getCountry());
            assertEquals(result.getCertificate().getSubjectName().getOrganization(), result2.getCertificate().getSubjectName().getOrganization());
            assertEquals(result.getCertificate().getSubjectName().getOrganizationUnit(), result2.getCertificate().getSubjectName().getOrganizationUnit());
            assertEquals(result.getCertificate().getSubjectName().getDnQualifier(), result2.getCertificate().getSubjectName().getDnQualifier());
            assertEquals(result.getCertificate().getSubjectName().getStateName(), result2.getCertificate().getSubjectName().getStateName());
            assertEquals(result.getCertificate().getSubjectName().getLocality(), result2.getCertificate().getSubjectName().getLocality());
            assertEquals(result.getCertificate().getSubjectName().getTitle(), result2.getCertificate().getSubjectName().getTitle());
            assertEquals(result.getCertificate().getSubjectName().getSurname(), result2.getCertificate().getSubjectName().getSurname());
        } catch (Exception e) {
            // If PKI Express is not available or certificate is invalid,
            // the test will fail but we've still tested the invoke() call path
            throw new AssertionError("Failed to execute open() with invoke() call: " + e.getMessage(), e);
        } finally {
            // Cleanup
            explorer.dispose();
        }
    }
}
