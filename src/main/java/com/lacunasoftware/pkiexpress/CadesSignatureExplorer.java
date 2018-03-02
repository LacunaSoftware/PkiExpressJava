package com.lacunasoftware.pkiexpress;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CadesSignatureExplorer extends SignatureExplorer {

    private Path dataFilePath;
    private Path extractedContentPath;


    public CadesSignatureExplorer(PkiExpressConfig config) {
        super(config);
    }

    public CadesSignatureExplorer() throws IOException {
        this(new PkiExpressConfig());
    }

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

    public void setExtractedContentPath(Path extractedContentPath) {
        this.extractedContentPath = extractedContentPath;
    }

    public Path getExtractedContentPath() {
        return extractedContentPath;
    }

    public CadesSignature open() throws IOException {

        if (signatureFilePath == null) {
            throw new RuntimeException("The provided signature file was not found");
        }

        List<String> args = new ArrayList<String>();
        args.add(signatureFilePath.toString());

        if (validate) {
            args.add("--validate");
        }

        if (dataFilePath != null) {
            args.add("--data-file");
            args.add(dataFilePath.toString());
        }

        if (extractedContentPath != null) {
            args.add("--extract-content");
            args.add(extractedContentPath.toString());
        }

        // This operation can only be used on versions greater than 1.3 of the PKI Express.
        this.versionManager.requireVersion(new Version("1.3"));

        // Invoke command
        OperatorResult result = invoke(CommandEnum.CommandOpenCades, args);

        // Parse output
        CadesSignatureModel resultModel = parseOutput(result.getOutput()[0], CadesSignatureModel.class);

        return new CadesSignature(resultModel);
    }
}
