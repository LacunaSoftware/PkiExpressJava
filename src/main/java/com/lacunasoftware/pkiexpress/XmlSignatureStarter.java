package com.lacunasoftware.pkiexpress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XmlSignatureStarter extends SignatureStarter {

    private Path xmlToSignPath;
    private String toSignElementId;
    private XmlSignaturePolicies signaturePolicy;

    public XmlSignatureStarter(PkiExpressConfig config) {
        super(config);
    }

    //region setXmlToSign
    public void setXmlToSign(InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024];
        Path tempPath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempPath.toFile());

        int nRead;
        while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
            outputStream.write(buff, 0, nRead);
        }
        outputStream.close();

        this.xmlToSignPath = tempPath;
    }

    public void setXmlToSign(byte[] content) throws IOException {
        setXmlToSign(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setXmlToSign(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided file to be signed was not found");
        }

        this.xmlToSignPath = path;
    }

    public void setXmlToSign(String path) throws IOException {
        setXmlToSign(Paths.get(path));
    }
    //endregion

    public void setToSignElementId(String toSignElementId) {
        this.toSignElementId = toSignElementId;
    }

    public void setSignaturePolicy(XmlSignaturePolicies signaturePolicy) {
        this.signaturePolicy = signaturePolicy;
    }

    public SignatureStartResult start() throws IOException {

        if (xmlToSignPath == null) {
            throw new RuntimeException("The XML to be signed was not set");
        }

        if (certificatePath == null) {
            throw new RuntimeException("The certificate was not set");
        }

        if (signaturePolicy == XmlSignaturePolicies.NFe && Util.isNullOrEmpty(toSignElementId)) {
            throw new RuntimeException("The signature element id to be signed was not set");
        }

        String transferFile = getTransferFileName();

        List<String> args = new ArrayList<String>();
        args.add(xmlToSignPath.toString());
        args.add(certificatePath.toString());
        args.add(config.getTransferDataFolder().resolve(transferFile).toString());

        if (signaturePolicy != null) {
            args.add("-p");
            args.add(signaturePolicy.getValue());

            if (signaturePolicy == XmlSignaturePolicies.NFe && !Util.isNullOrEmpty(toSignElementId)) {
                args.add("-eid");
                args.add(toSignElementId);
            }
        }

        OperatorResult result = invoke(CommandEnum.CommandStartXml, args);
        if (result.getResponse() != 0) {
            throw new RuntimeException(result.getOutput());
        }

        SignatureStartResult startResult = new SignatureStartResult();
        String[] outputLines = result.getOutput().split(System.getProperty("line.separator"));
        startResult.setToSignHash(outputLines[0]);
        startResult.setDigestAlgorithm(outputLines[1]);
        startResult.setDigestAlgorithmOid(outputLines[2]);
        startResult.setTransferFile(transferFile);
        return startResult;
    }
}
