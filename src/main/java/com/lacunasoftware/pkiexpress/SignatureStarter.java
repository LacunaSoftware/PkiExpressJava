package com.lacunasoftware.pkiexpress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SignatureStarter extends PkiExpressOperator {

    protected Path certificatePath;
    protected StandardSignaturePolicies signaturePolicy;
    protected TimestampAuthority timestampAuthority;


    public SignatureStarter(PkiExpressConfig config) {
        super(config);
    }

    public SignatureStarter() throws IOException {
        this(new PkiExpressConfig());
    }

    //region setCertificate
    public void setCertificate(InputStream inputStream) throws IOException {
        this.certificatePath = writeToTempFile(inputStream);
    }

    public void setCertificate(byte[] content) throws IOException {
        setCertificate(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setCertificate(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided certificate was not found");
        }

        this.certificatePath = path;
    }

    public void setCertificate(String path) throws IOException {
        setCertificate(Paths.get(path));
    }

    public void setCertificateBase64(String contentBase64) throws IOException {
        byte[] contentRaw = Util.decodeBase64(contentBase64);
        setCertificate(contentRaw);
    }

    //endregion

    public void setSignaturePolicy(StandardSignaturePolicies signaturePolicy) {
        this.signaturePolicy = signaturePolicy;
    }

    public void setSignaturePolicy(XmlSignaturePolicies signaturePolicy) {

        switch (signaturePolicy) {
            case NFe:
                this.signaturePolicy = StandardSignaturePolicies.NFePadraoNacional;
                break;
            case Basic:
                this.signaturePolicy = StandardSignaturePolicies.XmlDSigBasic;
                break;
            default:
                throw new RuntimeException("Invalid signature policy: " + signaturePolicy.getValue());
        }
    }

    public void setTimestampAuthority(TimestampAuthority timestampAuthority) {
        this.timestampAuthority = timestampAuthority;
    }

    protected SignatureStartResult getResult(String[] response, String transferFile) {
        SignatureStartResult startResult = new SignatureStartResult();
        startResult.setToSignHash(response[0]);
        startResult.setDigestAlgorithm(response[1]);
        startResult.setDigestAlgorithmOid(response[2]);
        startResult.setTransferFile(transferFile);
        return startResult;
    }

    protected void verifyAndAddCommonOptions(List<String> args) {

        if (StandardSignaturePolicies.requireTimestamp(signaturePolicy) && timestampAuthority == null) {
            throw new RuntimeException("The provided policy requires a timestamp authority and none was provided");
        }

        // Set signature policy.
        if (signaturePolicy != null) {
            args.add("--policy");
            args.add(signaturePolicy.getValue());

            // This operation can only be used on versions greater than 1.5 of the PKI Express.
            versionManager.requireVersion(new Version("1.5"));
        }


        // Add timestamp authority.
        if (timestampAuthority != null) {
            args.addAll(timestampAuthority.getCmdArguments());

            // This operation can only be used on versions greater than 1.5 of the PKI Express.
            versionManager.requireVersion(new Version("1.5"));
        }
    }

}
