package com.lacunasoftware.pkiexpress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XmlSigner extends Signer {

    private Path xmlToSignPath;
    private String toSignElementId;
    private XmlSignaturePolicies signaturePolicy;


    public XmlSigner(PkiExpressConfig config) {
        super(config);
    }

    public XmlSigner() throws IOException {
        this(new PkiExpressConfig());
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
        setXmlToSign(path != null ? Paths.get(path) : null);
    }
    //endregion

    public void setToSignElementId(String toSignElementId) {
        this.toSignElementId = toSignElementId;
    }

    public void setSignaturePolicy(XmlSignaturePolicies signaturePolicy) {
        this.signaturePolicy = signaturePolicy;
    }

    public void sign() throws IOException {

        if (xmlToSignPath == null) {
            throw new RuntimeException("The file to be signed was not set");
        }

        if (Util.isNullOrEmpty(certThumb)) {
            throw new RuntimeException("The certificate thumbprint was not set");
        }

        if (outputFilePath == null) {
            throw new RuntimeException("The output destination was not set");
        }

        if (signaturePolicy == XmlSignaturePolicies.NFe && Util.isNullOrEmpty(toSignElementId)) {
            throw new RuntimeException("The signature element id to be signed was not set");
        }

        List<String> args = new ArrayList<String>();
        args.add(xmlToSignPath.toString());
        args.add(certThumb);
        args.add(outputFilePath.toString());

        if (signaturePolicy != null) {
            args.add("-p");
            args.add(signaturePolicy.getValue());

            if (signaturePolicy == XmlSignaturePolicies.NFe && !Util.isNullOrEmpty(toSignElementId)) {
                args.add("-eid");
                args.add(toSignElementId);
            }
        }

        OperatorResult result = invoke(CommandEnum.CommandSignXml, args);
        if (result.getResponse() != 0) {
            StringBuilder sb = new StringBuilder();
            for (String line : result.getOutput()) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            throw new RuntimeException(sb.toString());
        }

    }
}
