package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for Authentication.
 * Tests all methods that contain invoke() calls.
 * Each method with an invoke() call gets its own test case.
 */
class PadesSignerIT {

    private PadesSigner signer;
    private Path outputFile;

    @BeforeEach
    void setUp() throws IOException {
        signer = new PadesSigner();
        outputFile = Files.createTempFile("pades-signer-test-", ".pdf");
        signer.setOutputFile(outputFile);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (signer != null) {
            signer.dispose();
        }
        Files.deleteIfExists(outputFile);
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
    @DisplayName("sign(true) should sign a PDF and return a valid signer certificate")
    void signWithInvokeCall_shouldProduceSignedPdfAndCertificate() throws Exception {
        // This test exercises sign(boolean getCert),
        // which internally calls invoke(CommandEnum.CommandSignPades, args).
        //
        // NOTE:
        // This requires:
        //  - A valid PDF
        //  - A valid PKCS#12 certificate
        //  - PKI Express available in the environment

        // If PKI Express is not available, skip instead of failing
        assumeTrue(PkiExpress.isAvailable(), "PKI Express is not available");

        // Load PDF from test resources
        InputStream pdfInputStream =
                getClass().getResourceAsStream("/resources/SamplePdf.pdf");

        assertNotNull(pdfInputStream, "SamplePdf.pdf should be present in test resources");

        // Configure signer
        signer.setPdfToSign(pdfInputStream);
        signer.setPkcs12(TestUtils.LoadSamplePkcs12AsPath());
        signer.setCertPassword(TestUtils.getSampleCertificatePassword());
        signer.setTrustLacunaTestRoot(true); // allow test certificates

        // Execute
        PKCertificate certificate = signer.sign(true);

        // ---- Certificate assertions ----
        assertNotNull(certificate, "Returned certificate should not be null");

        assertAll("Certificate fields should be populated",
            () -> assertNotNull(certificate.getThumbprint(), "Thumbprint"),
            () -> assertNotNull(certificate.getSubjectName(), "Subject name"),
            () -> assertNotNull(certificate.getIssuerName(), "Issuer name"),
            () -> assertNotNull(certificate.getSerialNumber(), "Serial number"),
            () -> assertNotNull(certificate.getValidityStart(), "Validity start"),
            () -> assertNotNull(certificate.getValidityEnd(), "Validity end"),
            () -> assertNotNull(certificate.getKeyUsage(), "Key usage"),
            () -> assertNotNull(certificate.getCertificatePolicies(), "Certificate policies"),
            () -> assertFalse(
                    certificate.getCertificatePolicies().isEmpty(),
                    "Certificate policies should not be empty"
            )
        );

        // Validate certificate fields against known sample certificate
        TestUtils.validateCertificateFieldsFromSampleCertificate(certificate);

        // ---- Output file assertions ----
        assertAll("Signed PDF output",
            () -> assertTrue(Files.exists(outputFile), "Output file should exist"),
            () -> assertTrue(Files.size(outputFile) > 0, "Output file should not be empty")
        );
    }
}
