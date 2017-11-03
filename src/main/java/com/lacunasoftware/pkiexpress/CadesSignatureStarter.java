package com.lacunasoftware.pkiexpress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CadesSignatureStarter extends SignatureStarter {

    private Path fileToSignPath;
    private Path dataFilePath;

    public boolean encapsulateContent;


    public CadesSignatureStarter(PkiExpressConfig config) {
        super(config);
        encapsulateContent = true;
    }

    //region setFileToSign
    public void setFileToSign(InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024];
        Path tempPath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempPath.toFile());

        int nRead;
        while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
            outputStream.write(buff, 0, nRead);
        }
        outputStream.close();

        this.fileToSignPath = tempPath;
    }

    public void setFileToSign(byte[] content) throws IOException {
        setFileToSign(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setFileToSign(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided file to be signed was not found");
        }

        this.fileToSignPath = path;
    }

    public void setFileToSign(String path) throws IOException {
        setFileToSign(Paths.get(path));
    }
    //endregion

    //region setDataFile
    public void setDataFile(InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024];
        Path tempPath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempPath.toFile());

        int nRead;
        while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
            outputStream.write(buff, 0, nRead);
        }
        outputStream.close();

        this.dataFilePath = tempPath;
    }

    public void setDataFile(byte[] content) throws IOException {
        setDataFile(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setDataFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided file to be signed was not found");
        }

        this.dataFilePath = path;
    }

    public void setDataFile(String path) throws IOException {
        setDataFile(Paths.get(path));
    }
    //endregion

    public SignatureStartResult start() throws IOException {

        if (fileToSignPath == null) {
            throw new RuntimeException("The file to be signed was not set");
        }

        if (certificatePath == null) {
            throw new RuntimeException("The certificate was not set");
        }

        // Generate transfer file
        String transferFile = getTransferFileName();

        List<String> args = new ArrayList<String>();
        args.add(fileToSignPath.toString());
        args.add(certificatePath.toString());
        args.add(config.getTransferDataFolder().resolve(transferFile).toString());

        if (dataFilePath != null) {
            args.add("-df");
            args.add(dataFilePath.toString());
        }

        if (!encapsulateContent) {
            args.add("-det");
        }

        OperatorResult result = invoke(CommandEnum.CommandStartCades, args);
        if (result.getResponse() != 0) {
            StringBuilder sb = new StringBuilder();
            for (String line : result.getOutput()) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            throw new RuntimeException(sb.toString());
        }

        SignatureStartResult startResult = new SignatureStartResult();
        startResult.setToSignHash(result.getOutput()[0]);
        startResult.setDigestAlgorithm(result.getOutput()[1]);
        startResult.setDigestAlgorithmOid(result.getOutput()[2]);
        startResult.setTransferFile(transferFile);
        return startResult;
    }
}
