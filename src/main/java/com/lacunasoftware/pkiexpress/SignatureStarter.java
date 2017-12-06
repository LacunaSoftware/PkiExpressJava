package com.lacunasoftware.pkiexpress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SignatureStarter extends PkiExpressOperator {

    protected Path certificatePath;

    public SignatureStarter(PkiExpressConfig config) {
        super(config);
    }

    public SignatureStarter() throws IOException {
        this(new PkiExpressConfig());
    }

    //region setCertificate
    public void setCertificate(InputStream inputStream) throws IOException {
        byte[] buff= new byte[1024];
        Path tempPath = createTempFile();
        OutputStream outputStream = new FileOutputStream(tempPath.toFile());

        int nRead;
        while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
            outputStream.write(buff, 0, nRead);
        }
        outputStream.close();

        this.certificatePath = tempPath;
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
    //endregion

    public void setCertificateBase64(String contentBase64) throws IOException {
        byte[] contentRaw = Util.decodeBase64(contentBase64);
        setCertificate(contentRaw);
    }
}
