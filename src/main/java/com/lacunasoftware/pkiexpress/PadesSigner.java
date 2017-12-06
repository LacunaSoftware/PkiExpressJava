package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PadesSigner extends Signer {

    private Path pdfToSignPath;
    private Path vrJsonPath;

    public boolean overwriteOriginalFile;


    public PadesSigner(PkiExpressConfig config) {
        super(config);
        overwriteOriginalFile = false;
    }

    public PadesSigner() throws IOException {
        this(new PkiExpressConfig());
    }

    //region setPdfToSign
    public void setPdfToSign(InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024];
        Path tempPath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempPath.toFile());

        int nRead;
        while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
            outputStream.write(buff, 0, nRead);
        }
        outputStream.close();

        this.pdfToSignPath = tempPath;
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

    //region setVisualRepresentationFromFile
    public void setVisualRepresentationFromFile(InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024];
        Path tempPath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempPath.toFile());

        int nRead;
        while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
            outputStream.write(buff, 0, nRead);
        }
        outputStream.close();

        this.vrJsonPath = tempPath;
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
    //endregion

    public void setVisualRepresentation(PadesVisualRepresentation visualRepresentation) throws IOException {
        Path tempPath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempPath.toFile());
        new ObjectMapper().writeValue(outputStream, visualRepresentation.toModel());
        outputStream.close();
        this.vrJsonPath = tempPath;
    }

    public void sign() throws IOException {

        if (pdfToSignPath == null) {
            throw new RuntimeException("The PDF to be signed was not set");
        }

        if (Util.isNullOrEmpty(certThumb)) {
            throw new RuntimeException("The certificate thumbprint was not set");
        }

        if (!overwriteOriginalFile && outputFilePath == null) {
            throw new RuntimeException("The output destination was not set");
        }

        List<String> args = new ArrayList<String>();
        args.add(pdfToSignPath.toString());
        args.add(certThumb);

        // Logic to overwrite original file or use the output file
        if (overwriteOriginalFile) {
            args.add("-ow");
        } else {
            args.add(outputFilePath.toString());
        }

        if (vrJsonPath != null) {
            args.add("-vr");
            args.add(vrJsonPath.toString());
        }

        OperatorResult result = invoke(CommandEnum.CommandSignPades, args);
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
