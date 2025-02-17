package com.lacunasoftware.pkiexpress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Metadata extends PkiExpressOperator {
	public static final String LIBRARY_VERSION = "1.21.4";

	public Metadata(PkiExpressConfig config) {
		super(config);
	}

	public Metadata() throws IOException {
	}

	public String getLibraryVersion() {
		return LIBRARY_VERSION;
	}

	public String getPkiExpressVersion() throws IOException {

		// Empty number of args.
		List<String> args = new ArrayList<>();

		this.versionManager.requireVersion(new Version("1.21"));

		// Invoke command.
		OperatorResult result = invoke(CommandEnum.CommandVersion, args);

		// Parse output and return model.
		VersionCommandResultModel model = parseOutput(result.getOutput()[0], VersionCommandResultModel.class);
		return model.getVersion();
	}
}
