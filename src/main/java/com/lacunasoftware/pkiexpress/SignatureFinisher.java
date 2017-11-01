package com.lacunasoftware.pkiexpress;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SignatureFinisher extends PkiExpressOperator {

    private Path fileToSignPath;
    private Path transferFilePath;
    private Path dataFilePath;
    private Path outputFilePath;
    private String signature;

    public SignatureFinisher(PkiExpressConfig config) {
        super(config);
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
            throw new FileNotFoundException("The provided data file was not found");
        }

        this.dataFilePath = path;
    }

    public void setDataFile(String path) throws IOException {
        setDataFile(Paths.get(path));
    }
    //endregion

    //region setTransferFilePath
    public void setTransferFilePath(Path path) {
        if (!Files.exists(config.getTransferDataFolder().resolve(path))) {
            throw new RuntimeException("The provided transfer file was not found");
        }
        this.transferFilePath = path;
    }

    public void setTransferFilePath(String path) {
        setTransferFilePath(Paths.get(path));
    }
    //endregion

    public void setSignature(String signature) {
        try {
            Util.decodeBase64(signature);
        } catch(Exception ex) {
            throw new RuntimeException("The provided signature was not valid");
        }

        this.signature = signature;
    }

    //region setOutputFilePath
    public void setOutputFilePath(Path path) {
        this.outputFilePath = path;
    }

    public void setOutputFilePath(String path) {
        setOutputFilePath(Paths.get(path));
    }
    //endregion

    public void complete() throws IOException {

        if (fileToSignPath == null) {
            throw new RuntimeException("The file to be signed was not set");
        }

        if (transferFilePath == null) {
            throw new RuntimeException("The transfer data file was not set");
        }

        if (Util.isNullOrEmpty(signature)) {
            throw new RuntimeException("The signature was not set");
        }

        if (outputFilePath == null) {
            throw new RuntimeException("The output destination was not set");
        }

        List<String> args = new ArrayList<String>();
        args.add(fileToSignPath.toString());
        args.add(config.getTransferDataFolder().resolve(transferFilePath).toString());
        args.add(signature);
        args.add(outputFilePath.toString());

        if (dataFilePath != null) {
            args.add("-df");
            args.add(dataFilePath.toString());
        }

        OperatorResult result = invoke(CommandEnum.CommandCompleteSig, args);
        if (result.getResponse() != 0) {
            throw new RuntimeException(result.getOutput());
        }
    }
}
