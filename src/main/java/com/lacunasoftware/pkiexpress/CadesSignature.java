package com.lacunasoftware.pkiexpress;


import java.util.ArrayList;
import java.util.List;


/**
 * Represents a CAdES signature.
 */
public class CadesSignature {
	protected List<CadesSignerInfo> signers = new ArrayList<CadesSignerInfo>();
	protected Boolean hasEncapsulatedContent;

	
	CadesSignature(CadesSignatureModel model) {
		for (CadesSignerModel signerModel : model.getSigners()) {
			this.signers.add(new CadesSignerInfo(signerModel));
		}
		this.hasEncapsulatedContent = model.getHasEncapsulatedContent();
	}

	public List<CadesSignerInfo> getSigners() {
		return signers;
	}

	public Boolean getHasEncapsulatedContent() {
		return hasEncapsulatedContent;
	}
}