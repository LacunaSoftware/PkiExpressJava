package com.lacunasoftware.pkiexpress;



/**
 * Represents a Commitment Type.
 */
public class CommitmentType {
	protected String oid;
	protected String name;
	
	CommitmentType(CommitmentTypeModel model) {
		this.oid = model.getOid();
		this.name = model.getName();
	}

	public String getOid() {
		return oid;
	}

	public String getName() {
		return name;
	}
}