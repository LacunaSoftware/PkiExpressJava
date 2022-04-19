package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

public class CertificatePolicy {

	private String id;
	private List<CertificatePolicyQualifierInfo> qualifiers = new ArrayList<CertificatePolicyQualifierInfo>();

	CertificatePolicy(CertificatePolicyModel model) {
		this.id = model.getId();
		for (CertificatePolicyQualifierInfoModel qualifierModel : model.getQualifiers()) {
			this.qualifiers.add(new CertificatePolicyQualifierInfo(qualifierModel));
		}
	}

	public String getId() {
		return this.id;
	}

	public List<CertificatePolicyQualifierInfo> getQualifiers() {
		return this.qualifiers;
	}
}