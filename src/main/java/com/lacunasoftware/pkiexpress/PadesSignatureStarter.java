package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PadesSignatureStarter extends SignatureStarter {

    private Path pdfToSignPath;
    private Path vrJsonPath;


    public PadesSignatureStarter(PkiExpressConfig config) {
        super(config);
    }

    public PadesSignatureStarter() throws IOException {
        this(new PkiExpressConfig());
    }

    //region setPdfToSign
    public void setPdfToSign(InputStream inputStream) throws IOException {
        this.pdfToSignPath = writeToTempFile(inputStream);
    }

    public void setPdfToSign(byte[] content) throws IOException {
        setPdfToSign(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setPdfToSign(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided PDF to be signed was not found");
        }

        this.pdfToSignPath = path;
    }

    public void setPdfToSign(String path) throws IOException {
        setPdfToSign(path != null ? Paths.get(path) : null);
    }
    //endregion

    //region setVisualRepresentation
    public void setVisualRepresentationFromFile(InputStream inputStream) throws IOException {
        this.vrJsonPath = writeToTempFile(inputStream);
    }

    public void setVisualRepresentationFromFile(byte[] content) throws IOException {
        setVisualRepresentationFromFile(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setVisualRepresentationFromFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided visual representation file was not found");
        }
        this.vrJsonPath = path;
    }

    public void setVisualRepresentationFromFile(String path) throws IOException {
        setVisualRepresentationFromFile(path != null ? Paths.get(path) : null);
    }

    public void setVisualRepresentation(PadesVisualRepresentation visualRepresentation) throws IOException {
        Path tempPath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempPath.toFile());
        new ObjectMapper().writeValue(outputStream, visualRepresentation.toModel());
        outputStream.close();
        this.vrJsonPath = tempPath;
    }
    //endregion

    public SignatureStartResult start() throws IOException {

        if (pdfToSignPath == null) {
            throw new RuntimeException("The PDF to be signed was not set");
        }

        if (certificatePath == null) {
            throw new RuntimeException("The certificate was not set");
        }

        // Generate transfer file
        String transferFile = getTransferFileName();

        List<String> args = new ArrayList<String>();
        args.add(pdfToSignPath.toString());
        args.add(certificatePath.toString());
        args.add(config.getTransferDataFolder().resolve(transferFile).toString());

        if (vrJsonPath != null) {
            args.add("--visual-rep");
            args.add(vrJsonPath.toString());
        }

        // Invoke command with plain text output (to support PKI Express < 1.3)
        String[] response = invokePlain(CommandEnum.CommandStartPades, args);

        // Parse output
        return getResult(response, transferFile);
    }
}
