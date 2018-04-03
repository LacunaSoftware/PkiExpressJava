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

public class Authentication extends PkiExpressOperator {

    private Path nonceStorePath;
    private String nonceBase64;
    private Path certificatePath;
    private String signatureBase64;
    private boolean useExternalStorage = true;


    public Authentication(PkiExpressConfig config) {
        super(config);
    }

    public Authentication() throws IOException {
        this(new PkiExpressConfig());
    }

    public void setNonceStore(Path path) throws IOException {

        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new FileNotFoundException("The provided nonce store path was not found");
        }
        this.nonceStorePath = path;
    }

    public Path getNonceStore() {
        return nonceStorePath;
    }

    public String getNonce() {
        return nonceBase64;
    }

    public void setNonce(String nonceBase64) {
        try {
            Util.decodeBase64(nonceBase64);
        } catch(Exception ex) {
            throw new RuntimeException("The provided signature was not valid");
        }

        this.nonceBase64 = nonceBase64;
    }

    //region setCertificate

    public void setCertificate(InputStream inputStream) throws IOException {
        this.certificatePath = writeToTempFile(inputStream);
    }

    public void setCertificate(byte[] content) throws IOException {
        setCertificate(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setCertificate(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The provided certificate was not found");
        }

        this.certificatePath = path;
    }

    public void setCertificate(String path) throws IOException {
        setCertificate(Paths.get(path));
    }

    public void setCertificateBase64(String contentBase64) throws IOException {
        byte[] contentRaw = Util.decodeBase64(contentBase64);
        setCertificate(contentRaw);
    }

    //endregion

    public void setSignature(String signatureBase64) {
        try {
            Util.decodeBase64(signatureBase64);
        } catch(Exception ex) {
            throw new RuntimeException("The provided signature was not valid");
        }

        this.signatureBase64 = signatureBase64;
    }

    public boolean getUseExternalStorage() {
        return useExternalStorage;
    }

    public void setExternalStorage(boolean useExternalStorage) {
        this.useExternalStorage = useExternalStorage;
    }

    public AuthStartResult start() throws IOException {

        if (!useExternalStorage && nonceStorePath == null) {
            throw new RuntimeException("The option to use internal storage and no nonce store was provided");
        }

        List<String> args = new ArrayList<String>();

        // Add nonce store path based on user option.
        addNonceStoreOption(args);

        // This operation can only be used on versions greater than 1.4 of the PKI Express.
        this.versionManager.requireVersion(new Version("1.4"));

        // Invoke command with plain text output (to support PKI Express < 1.3)
        OperatorResult response = invoke(CommandEnum.CommandStartAuth, args);

        // Parse output and return result
        AuthStartModel model = parseOutput(response.getOutput()[0], AuthStartModel.class);
        return new AuthStartResult(model);
    }

    public AuthenticationResult complete() throws IOException {

        if (nonceBase64 == null) {
            throw new RuntimeException("The nonce value was not set");
        }

        if (certificatePath == null) {
            throw new RuntimeException("The certificate file was not set");
        }

        if (signatureBase64 == null) {
            throw new RuntimeException("The signature was not set");
        }

        if (!useExternalStorage && nonceStorePath == null) {
            throw new RuntimeException("Chose to use internal storage and no nonce store was provided");
        }

        List<String> args = new ArrayList<String>();
        args.add(nonceBase64);
        args.add(certificatePath.toString());
        args.add(signatureBase64);

        // Add nonce store path based on user option.
        addNonceStoreOption(args);

        // This operation can only be used on versions greater than 1.4 of the PKI Express.
        this.versionManager.requireVersion(new Version("1.4"));

        // Invoke command
        OperatorResult response = invoke(CommandEnum.CommandCompleteAuth, args);

        // Parse output and return model
        AuthCompleteModel model = parseOutput(response.getOutput()[0], AuthCompleteModel.class);
        return new AuthenticationResult(model);
    }

    private void addNonceStoreOption(List<String> args) {

        if (useExternalStorage) {
            // Use transfer data folder, when the option "use external storage" is set.
            args.add("--nonce-store");
            args.add(config.getTransferDataFolder().toString());
        } else {
            args.add("--nonce-store");
            args.add(nonceStorePath.toString());
        }

    }
}
