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

    @Deprecated
    public Boolean encapsulateContent = true;


    public CadesSignatureStarter(PkiExpressConfig config) {
        super(config);
    }

    public CadesSignatureStarter() throws IOException {
        this(new PkiExpressConfig());
    }

    //region setFileToSign
    public void setFileToSign(InputStream inputStream) throws IOException {
        this.fileToSignPath = writeToTempFile(inputStream);
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

    //region setDataFile
    public void setDataFile(InputStream inputStream) throws IOException {
        this.dataFilePath = writeToTempFile(inputStream);
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
        setDataFile(path != null ? Paths.get(path) : null);
    }
    //endregion

    public Boolean getEncapsulateContent() {
        return encapsulateContent;
    }

    public void setEncapsulateContent(Boolean encapsulateContent) {
        this.encapsulateContent = encapsulateContent;
    }

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

        // Invoke command with plain text output (to support PKI Express < 1.3)
        String[] response = invokePlain(CommandEnum.CommandStartCades, args);

        // Parse output
        return getResult(response, transferFile);
    }
}
