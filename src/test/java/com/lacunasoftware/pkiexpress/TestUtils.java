package com.lacunasoftware.pkiexpress;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;

public class TestUtils {
    public static InputStream LoadSampleCertificate() {
        return TestUtils.class.getResourceAsStream("resources/AlanTuring.cer");
    }

    public static InputStream LoadSamplePdf() {
        return TestUtils.class.getResourceAsStream("resources/SamplePdf.pdf");
    }

    public static void validateCertificateFieldsFromSampleCertificate(PKCertificate certificate) {
        assertTrue("certificate common name should be Alan Mathison Turing", certificate.getSubjectName().getCommonName().equals("Alan Mathison Turing"));
        assertTrue("certificate issuer name should be Lacuna CA Test v7", certificate.getIssuerName().getCommonName().equals("Lacuna CA Test v7"));
        assertTrue("certificate country should be BR", certificate.getIssuerName().getCountry().equals("BR"));
        assertTrue("certificate thumbprint should be 399f766d48950dfaafcd8eb84bf6acbd440acb34", certificate.getThumbprint().equals("399f766d48950dfaafcd8eb84bf6acbd440acb34"));
        assertTrue("certificate email address should be 'test@turing@lacunasoftware.com'", certificate.getEmailAddress().equals("test@turing@lacunasoftware.com"));
        assertTrue("certificate organization should be Lacuna Software", certificate.getIssuerName().getOrganization().equals("Lacuna Software"));
        assertTrue("certificate organization unit should be IT", certificate.getIssuerName().getOrganizationUnit().equals("IT"));
        assertTrue("certificate validityStart should be Fri Jan 03 14:16:57 GMT 2025", certificate.getValidityStart().toString().equals("Fri Jan 03 14:16:57 GMT 2025"));
        assertTrue("certificate validityEnd should be Thu Jan 03 14:21:14 GMT 2075", certificate.getValidityEnd().toString().equals("Thu Jan 03 14:21:14 GMT 2075"));
        
        // Pki Brazil Certificate Fields
        assertTrue("certificate cpf should be '56072386105' ", certificate.getPkiBrazil().getCpf().equals("56072386105"));
        assertTrue("certificate type should be A1", certificate.getPkiBrazil().getCertificateType().equals(PkiBrazilCertificateTypes.A1));
        assertTrue("Certificate responsavel should be 'Alan Mathison Turing' ", certificate.getPkiBrazil().getResponsavel().equals("Alan Mathison Turing"));
        assertTrue("Certificate date of birth should be 'Sat Jan 01 00:00:00 GMT 2000' ", certificate.getPkiBrazil().getDateOfBirth().toString().equals("Sat Jan 01 00:00:00 GMT 2000"));
    }
}
