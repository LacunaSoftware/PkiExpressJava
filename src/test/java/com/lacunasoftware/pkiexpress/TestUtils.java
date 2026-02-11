package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.nio.file.Path;

public class TestUtils {

    public static InputStream LoadSampleCertificateAsDERFormat() {
        return TestUtils.class.getResourceAsStream("resources/AlanTuring-DER.cer");
    }

    public static InputStream LoadSampleCertificateAsPEMFormat() {
        return TestUtils.class.getResourceAsStream("resources/AlanTuring-PEM.cer");
    }

    public static Path LoadSamplePkcs12AsPath() {
        try {
            return Path.of(TestUtils.class.getResource("resources/Alan Mathison Turing.pfx").toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sample PKCS12 file: " + e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }

    public static String getSampleCertificatePassword() {
        return "1234";
    }

    public static InputStream LoadSamplePdf() {
        return TestUtils.class.getResourceAsStream("resources/SamplePdf.pdf");
    }

    public static Path LoadSignedSamplePdf() {
        try {
            return Path.of(TestUtils.class.getResource("resources/SignedSamplePdf.pdf").toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load signed sample PDF file: " + e.getMessage(), e);
        }
    }

    public static Path LoadSampleXml() {
        try {
            return Path.of(TestUtils.class.getResource("resources/SampleNFe.xml").toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sample XML file: " + e.getMessage(), e);
        }
    }

    public static Path LoadSignedSampleCmsFile() {
        try {
            return Path.of(TestUtils.class.getResource("resources/signedCMS.p7s").toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sample CMS file: " + e.getMessage(), e);
        }
    }

    public static Path LoadSignedSecondSampleCmsFile() {
        try {
            return Path.of(TestUtils.class.getResource("resources/signedCMS_2.p7s").toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sample CMS file: " + e.getMessage(), e);
        }
    }

    public static void validateCertificateFieldsFromSampleCertificate(PKCertificate certificate) {
        assertTrue("certificate common name should be Alan Mathison Turing", certificate.getSubjectName().getCommonName().equals("Alan Mathison Turing"));
        assertTrue("certificate issuer name should be Lacuna CA Test v7", certificate.getIssuerName().getCommonName().equals("Lacuna CA Test v7"));
        assertTrue("certificate country should be BR", certificate.getIssuerName().getCountry().equals("BR"));
        assertTrue("certificate thumbprint should be 399f766d48950dfaafcd8eb84bf6acbd440acb34", certificate.getThumbprint().equals("399f766d48950dfaafcd8eb84bf6acbd440acb34"));
        assertTrue("certificate email address should be 'testturing@lacunasoftware.com'", certificate.getEmailAddress().equals("testturing@lacunasoftware.com"));
        assertTrue("certificate organization should be Lacuna Software", certificate.getIssuerName().getOrganization().equals("Lacuna Software"));
        assertTrue("certificate organization unit should be IT", certificate.getIssuerName().getOrganizationUnit().equals("IT"));
        assertTrue("certificate validityStart should be Fri Jan 03 14:16:57 UTC 2025", 
        certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 UTC 2025") 
        || certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 GMT 2025"));
        assertTrue("certificate validityEnd should be Thu Jan 03 14:21:14 UTC 2075", 
        certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 UTC 2075")
        || certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 GMT 2075"));
        
        // Pki Brazil Certificate Fields
        assertTrue("certificate cpf should be '56072386105' ", certificate.getPkiBrazil().getCpf().equals("56072386105"));
        assertTrue("certificate type should be A1", certificate.getPkiBrazil().getCertificateType().equals(PkiBrazilCertificateTypes.A1));
        assertTrue("Certificate responsavel should be 'Alan Mathison Turing' ", certificate.getPkiBrazil().getResponsavel().equals("Alan Mathison Turing"));
        assertTrue("Certificate date of birth should be 'Sat Jan 01 00:00:00 UTC 2000' ", 
        certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 UTC 2000")
        || certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 GMT 2000"));
    }

    public static void validateCertificateFieldsFromSampleCertificate(PKCertificate certificate, boolean IsSignatureCertificate) {
        String messagePrefix = IsSignatureCertificate ? "signature certificate" : "certificate";
        assertTrue(messagePrefix + " common name should be Alan Mathison Turing", certificate.getSubjectName().getCommonName().equals("Alan Mathison Turing"));
        assertTrue(messagePrefix + " issuer name should be Lacuna CA Test v7", certificate.getIssuerName().getCommonName().equals("Lacuna CA Test v7"));
        assertTrue(messagePrefix + " country should be BR", certificate.getIssuerName().getCountry().equals("BR"));
        assertTrue(messagePrefix + " thumbprint should be 399f766d48950dfaafcd8eb84bf6acbd440acb34", certificate.getThumbprint().equals("399f766d48950dfaafcd8eb84bf6acbd440acb34"));
        assertTrue(messagePrefix + " email address should be 'testturing@lacunasoftware.com'", certificate.getEmailAddress().equals("testturing@lacunasoftware.com"));
        assertTrue(messagePrefix + " organization should be Lacuna Software", certificate.getIssuerName().getOrganization().equals("Lacuna Software"));
        assertTrue(messagePrefix + " organization unit should be IT", certificate.getIssuerName().getOrganizationUnit().equals("IT"));
        assertTrue(messagePrefix + " validityStart should be Fri Jan 03 14:16:57 GMT 2025", certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 GMT 2025")
        || certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 UTC 2025"));
        assertTrue(messagePrefix + " validityEnd should be Thu Jan 03 14:21:14 GMT 2075", certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 GMT 2075")
        || certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 UTC 2075"));
        
        // Pki Brazil Certificate Fields
        assertTrue(messagePrefix + " cpf should be '56072386105' ", certificate.getPkiBrazil().getCpf().equals("56072386105"));
        assertTrue(messagePrefix + " type should be A1", certificate.getPkiBrazil().getCertificateType().equals(PkiBrazilCertificateTypes.A1));
        assertTrue(messagePrefix + " responsavel should be 'Alan Mathison Turing' ", certificate.getPkiBrazil().getResponsavel().equals("Alan Mathison Turing"));
        assertTrue(messagePrefix + " date of birth should be 'Sat Jan 01 00:00:00 GMT 2000' ", certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 GMT 2000")
        || certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 UTC 2000"));
    }
}
