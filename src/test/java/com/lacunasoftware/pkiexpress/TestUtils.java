package com.lacunasoftware.pkiexpress;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(certificate.getSubjectName().getCommonName().equals("Alan Mathison Turing"), "certificate common name should be Alan Mathison Turing");
        assertTrue(certificate.getIssuerName().getCommonName().equals("Lacuna CA Test v7"), "certificate issuer name should be Lacuna CA Test v7");
        assertTrue(certificate.getIssuerName().getCountry().equals("BR"), "certificate country should be BR");
        assertTrue(certificate.getThumbprint().equals("399f766d48950dfaafcd8eb84bf6acbd440acb34"), "certificate thumbprint should be 399f766d48950dfaafcd8eb84bf6acbd440acb34");
        assertTrue(certificate.getEmailAddress().equals("testturing@lacunasoftware.com"), "certificate email address should be 'testturing@lacunasoftware.com'");
        assertTrue(certificate.getIssuerName().getOrganization().equals("Lacuna Software"), "certificate organization should be Lacuna Software");
        assertTrue(certificate.getIssuerName().getOrganizationUnit().equals("IT"), "certificate organization unit should be IT");
        assertTrue(certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 UTC 2025") 
        || certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 GMT 2025"), "certificate validityStart should be Fri Jan 03 14:16:57 UTC 2025");
        assertTrue(certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 UTC 2075")
        || certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 GMT 2075"), "certificate validityEnd should be Thu Jan 03 14:21:14 UTC 2075");
        
        // Pki Brazil Certificate Fields
        assertTrue(certificate.getPkiBrazil().getCpf().equals("56072386105"), "certificate cpf should be '56072386105' ");
        assertTrue(certificate.getPkiBrazil().getCertificateType().equals(PkiBrazilCertificateTypes.A1), "certificate type should be A1");
        assertTrue(certificate.getPkiBrazil().getResponsavel().equals("Alan Mathison Turing"), "Certificate responsavel should be 'Alan Mathison Turing' ");
        assertTrue(certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 UTC 2000")
        || certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 GMT 2000"), "Certificate date of birth should be 'Sat Jan 01 00:00:00 UTC 2000' ");
    }

    public static void validateCertificateFieldsFromSampleCertificate(PKCertificate certificate, boolean IsSignatureCertificate) {
        String messagePrefix = IsSignatureCertificate ? "signature certificate" : "certificate";
        assertTrue(certificate.getSubjectName().getCommonName().equals("Alan Mathison Turing"), messagePrefix + " common name should be Alan Mathison Turing");
        assertTrue(certificate.getIssuerName().getCommonName().equals("Lacuna CA Test v7"), messagePrefix + " issuer name should be Lacuna CA Test v7");
        assertTrue(certificate.getIssuerName().getCountry().equals("BR"), messagePrefix + " country should be BR");
        assertTrue(certificate.getThumbprint().equals("399f766d48950dfaafcd8eb84bf6acbd440acb34"), messagePrefix + " thumbprint should be 399f766d48950dfaafcd8eb84bf6acbd440acb34");
        assertTrue(certificate.getEmailAddress().equals("testturing@lacunasoftware.com"), messagePrefix + " email address should be 'testturing@lacunasoftware.com'");
        assertTrue(certificate.getIssuerName().getOrganization().equals("Lacuna Software"), messagePrefix + " organization should be Lacuna Software");
        assertTrue(certificate.getIssuerName().getOrganizationUnit().equals("IT"), messagePrefix + " organization unit should be IT");
        assertTrue(certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 GMT 2025")
        || certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 UTC 2025"), messagePrefix + " validityStart should be Fri Jan 03 14:16:57 GMT 2025");
        assertTrue(certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 GMT 2075")
        || certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 UTC 2075"), messagePrefix + " validityEnd should be Thu Jan 03 14:21:14 GMT 2075");
        
        // Pki Brazil Certificate Fields
        assertTrue(certificate.getPkiBrazil().getCpf().equals("56072386105"), messagePrefix + " cpf should be '56072386105' ");
        assertTrue(certificate.getPkiBrazil().getCertificateType().equals(PkiBrazilCertificateTypes.A1), messagePrefix + " type should be A1");
        assertTrue(certificate.getPkiBrazil().getResponsavel().equals("Alan Mathison Turing"), messagePrefix + " responsavel should be 'Alan Mathison Turing' ");
        assertTrue(certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 GMT 2000")
        || certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 UTC 2000"), messagePrefix + " date of birth should be 'Sat Jan 01 00:00:00 GMT 2000' ");
    }
}
