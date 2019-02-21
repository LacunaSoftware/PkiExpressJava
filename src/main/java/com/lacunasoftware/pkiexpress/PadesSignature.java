package com.lacunasoftware.pkiexpress;


import java.util.ArrayList;
import java.util.List;


/**
 * Represents a PAdES (PDF) signature.
 */
public class PadesSignature {
	protected List<PadesSignerInfo> signers = new ArrayList<PadesSignerInfo>();


	PadesSignature(PadesSignatureModel model) {
		for (PadesSignerModel signerModel : model.getSigners()) {
			signers.add(new PadesSignerInfo(signerModel));
		}
	}

	public List<PadesSignerInfo> getSigners() {
		return signers;
	}
}
