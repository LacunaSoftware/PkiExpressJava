package com.lacunasoftware.pkiexpress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CadesSignatureExplorer extends SignatureExplorer {

    public CadesSignatureExplorer(PkiExpressConfig config) {
        super(config);
    }

    public CadesSignatureExplorer() throws IOException {
        this(new PkiExpressConfig());
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

        // This operation can only be used on versions greater than 1.3 of the PKI Express.
        this.versionManager.requireVersion(new Version("1.3"));

        // Invoke command
        OperatorResult result = invoke(CommandEnum.CommandOpenCades, args);

        // Parse output
        CadesSignatureModel resultModel = parseOutput(result.getOutput()[0], CadesSignatureModel.class);

        return new CadesSignature(resultModel);
    }
}
