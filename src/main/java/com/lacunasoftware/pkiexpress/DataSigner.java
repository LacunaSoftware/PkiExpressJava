package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DataSigner extends Signer {
    
    private String toSignData;

    public String getToSignData() {
        return this.toSignData;
    }
    
    public void setToSignData(String toSignData) {
        this.toSignData = toSignData;
    }

    public DataSigner(PkiExpressConfig config) {
		super(config);
	}
	public DataSigner() throws IOException {
		this(new PkiExpressConfig());
	}

    public byte[] sign() throws IOException {
        if (toSignData == null) {
            throw new RuntimeException("The \"toSignData\" argument was not set");
        }

        List<String> args = new ArrayList<String>();

		// Verify and add common options between signers
        args.add(toSignData);
		verifyAndAddCommonOptions(args);
        
        // Invoke command.
        OperatorResult result = invoke(CommandEnum.CommandSignData, args);

        // Parse output and return model.
        byte[] output = Base64.getDecoder().decode(result.getOutput()[0]);
        return output;
    }
}
