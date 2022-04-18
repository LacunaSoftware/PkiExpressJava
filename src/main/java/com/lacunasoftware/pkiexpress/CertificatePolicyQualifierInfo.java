package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

public class CertificatePolicyQualifierInfo {

	public enum QualifierTypes {
		Cps, UserNotice;
	}

	private QualifierTypes type;
	private String cpsUri;
	private String unOrganization;
	private String unExplicitText;
	private List<Integer> unNoticeNumbers = new ArrayList<Integer>();

	CertificatePolicyQualifierInfo(CertificatePolicyQualifierInfoModel model) {
		this.type = QualifierTypes.valueOf(model.getType().toString());
		this.cpsUri = model.getCpsUri();
		this.unOrganization = model.getUnOrganization();
		this.unExplicitText = model.getUnExplicitText();
		this.unNoticeNumbers = model.getUnNoticeNumbers();
	}

	public QualifierTypes getType() {
		return this.type;
	}

	public String getCpsUri() {
		return this.cpsUri;
	}

	public String getUnOrganization() {
		return this.unOrganization;
	}

	public String getUnExplicitText() {
		return this.unExplicitText;
	}

	public List<Integer> getUnNoticeNumbers() {
		return this.unNoticeNumbers;
	}
}