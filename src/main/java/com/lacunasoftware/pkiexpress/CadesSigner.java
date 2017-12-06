package com.lacunasoftware.pkiexpress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CadesSigner extends Signer {

    private Path fileToSignPath;
    private Path dataFilePath;

    public boolean encapsulateContent;


    public CadesSigner(PkiExpressConfig config) {
        super(config);
        encapsulateContent = true;
    }

    public CadesSigner() throws IOException {
        this(new PkiExpressConfig());
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
        setFileToSign(path != null ? Paths.get(path) : null);
    }
    //endregion

    //region setDatafile
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
        setDataFile(path != null ? Paths.get(path) : null);
    }
    //endregion

    public void sign() throws IOException {

        if (fileToSignPath == null) {
            throw new RuntimeException("The file to be signed was not set");
        }

        if (Util.isNullOrEmpty(certThumb)) {
            throw new RuntimeException("The certificate thumbprint was not set");
        }

        if (outputFilePath == null) {
            throw new RuntimeException("The output destination was not set");
        }

        List<String> args = new ArrayList<String>();
        args.add(fileToSignPath.toString());
        args.add(certThumb);
        args.add(outputFilePath.toString());

        if (dataFilePath != null) {
            args.add("-df");
            args.add(dataFilePath.toString());
        }

        if (!encapsulateContent) {
            args.add("-det");
        }

        OperatorResult result = invoke(CommandEnum.CommandSignCades, args);
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
