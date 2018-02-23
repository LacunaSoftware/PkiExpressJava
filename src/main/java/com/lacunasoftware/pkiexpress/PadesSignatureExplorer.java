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

public class PadesSignatureExplorer extends PkiExpressOperator {

    private Path signatureFilePath;
    private boolean validate;

    public PadesSignatureExplorer(PkiExpressConfig config) {
        super(config);
    }

    public void setSignatureFile(InputStream inputStream) throws IOException {
        this.signatureFilePath = writeToTempFile(inputStream);
    }

    public void setSignatureFile(byte[] content) throws IOException {
        setSignatureFile(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setSignatureFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided file to be signed was not found");
        }

        this.signatureFilePath = path;
    }

    public void setSignatureFile(String path) throws IOException {
        setSignatureFile(path != null ? Paths.get(path) : null);
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public PadesSignature open() throws IOException {

        if (signatureFilePath == null) {
            throw new RuntimeException("The provided signature file was not found");
        }

        List<String> args = new ArrayList<String>();
        args.add(signatureFilePath.toString());

        if (validate) {
            args.add("--validate");
        }

        // This operation can only be used on versions greaters than 1.3 of the PKI Express.
        this.versionManager.requireVersion(new Version("1.3"));

        // Invoke command
        OperatorResult result = invoke(CommandEnum.CommandOpenPades, args);

        // Parse output
        PadesSignatureModel resultModel = parseOutput(result.getOutput()[0], PadesSignatureModel.class);

        return new PadesSignature(resultModel);
    }
}
